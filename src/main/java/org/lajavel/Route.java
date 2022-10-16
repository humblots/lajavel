package org.lajavel;

import io.javalin.http.Context;

import java.lang.reflect.Method;

public class Route {
    public static void register(HttpVerb verb, String routeName, Class<?> controllerClass, String methodName) {
        Application app = Application.getInstance();
        switch (verb) {
            case GET -> app.server.get(routeName, ctx -> invokeController(ctx, controllerClass, methodName));
            case POST -> app.server.post(routeName, ctx -> invokeController(ctx, controllerClass, methodName));
            case PUT -> app.server.put(routeName, ctx -> invokeController(ctx, controllerClass, methodName));
            case DELETE -> app.server.delete(routeName, ctx -> invokeController(ctx, controllerClass, methodName));
            case PATCH -> app.server.patch(routeName, ctx -> invokeController(ctx, controllerClass, methodName));
        }
    }

    private static void invokeController(Context ctx, Class<?> controllerClass, String methodName) {
        try {
            Response response = new Response(ctx);

            Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
            Method controllerMethod = controllerClass.getMethod(methodName, response.getClass());
            controllerMethod.invoke(controller, response);
        } catch (Exception e) {
            Log.error(e.toString());
        }
    }
}
