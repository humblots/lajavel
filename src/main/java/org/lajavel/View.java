package org.lajavel;

import app.MyApp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {
    // Default make
    public static String make(String viewName, Map.Entry<String, Object>... entries) {
        String layoutHtml = View.getViewContent("layouts", "main");
        String viewHtml = View.getViewContent("views", viewName);

        String rawHtml = layoutHtml.replaceFirst("\\{\\{\s*?content\s*?}}", viewHtml);

        String html = handleForeachesReplacement(rawHtml, entries);
        return handlePropertiesReplacement(html, entries);
    }

    // make with Layout parameter
    public static String make(String viewName, String layout, Map.Entry<String, Object>... entries) {
        String layoutHtml = View.getViewContent("layouts", layout);
        String viewHtml = View.getViewContent("views", viewName);

        String rawHtml = layoutHtml.replaceFirst("\\{\\{\s?content\s?}}", viewHtml);

        String html = handleForeachesReplacement(rawHtml, entries);
        return handlePropertiesReplacement(html, entries);
    }


    private static String handleForeachesReplacement(String html, Map.Entry<String, Object>... entries) {
        Matcher matcher = Pattern
                .compile("\\{%\s*?for\s(\\w*)\sin\s(\\w*)\s*?%}(.*)\\{%\s*?endfor\s*?%}", Pattern.DOTALL)
                .matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String objectName = matcher.group(1);
            String listName = matcher.group(2);
            String content = matcher.group(3);
            StringBuffer replacementSb = new StringBuffer();

            try {
                for (Map.Entry<String, Object> entry : entries) {
                    if (!entry.getKey().equals(listName)) {
                        continue;
                    }
                    for (Object model : (List<?>) entry.getValue()) {
                        if (model instanceof List<?>) {
                            replacementSb.append(handleForeachesReplacement(content, Map.entry(objectName, model)));
                        } else {
                            replacementSb.append(handlePropertiesReplacement(content, Map.entry(objectName, model)));
                        }
                    }
                    matcher.appendReplacement(sb, replacementSb.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String handlePropertiesReplacement(String html, Map.Entry<String, Object>... entries) {
        Matcher matcher = Pattern.compile("\\{\\{(.*?)}}").matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String[] ObjectAndProperty = matcher.group(1).trim().split("\\.");
            if (ObjectAndProperty.length <= 1) {
                throw new RuntimeException("You must specify an object and a property in your html");
            }

            String objectName = ObjectAndProperty[0];
            String propertyName = ObjectAndProperty[1];
            try {
                for (Map.Entry<String, Object> entry : entries) {
                    if (entry.getKey().equals(objectName)) {
                        matcher.appendReplacement(sb, getValueOf(entry.getValue(), propertyName));
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String getViewContent(String path, String viewName) {
        URL resource = MyApp.class.getClassLoader().getResource(path + '/' + viewName + ".javel");

        if (resource == null) {
            throw new RuntimeException("File " + viewName + " not found");
        }

        try {
            return Files.readString(Path.of(resource.toURI()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getValueOf(Object object, String propertyName) {
        propertyName = propertyName.replaceAll("\\s+", "");

        boolean isMethod = false;
        if (propertyName.contains("()")) {
            isMethod = true;
            propertyName = propertyName.replace("()", "");
        }

        if (isMethod) {
            return getMethodValue(String.class, object, propertyName);
        } else {
            Object returnValue = getPropertyValue(Object.class, object, propertyName);
            return returnValue.toString();
        }
    }

    /**
     * @param object
     * @param propertyName
     * @return the value of the property
     */
    private static <T> T getPropertyValue(Class<T> clazz, Object object, String propertyName) {
        Object returnValue = null;
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            returnValue = clazz.cast(field.get(object)).toString();
        } catch (Exception e) {
            Log.error(e.toString());
        }
        return clazz.cast(returnValue);
    }

    private static <T> T getMethodValue(Class<T> clazz, Object object, String methodName) {
        Object returnValue = null;
        try {
            Method method = object.getClass().getDeclaredMethod(methodName.replace("()", ""));
            method.setAccessible(true);
            returnValue = clazz.cast(method.invoke(object).toString());
        } catch (Exception e) {
            // Do nothing
            Log.error(e.toString());
        }
        return clazz.cast(returnValue);
    }
}
