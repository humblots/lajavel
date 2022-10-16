package org.lajavel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log {
    private static Log instance;

    private final Logger logger;


    private Log() {
        this.logger = LoggerFactory.getLogger("lajavel");
    }

    private static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public static void info(String message) {
        print(Level.INFO, message);
    }

    public static void error(String message) {
        print(Level.ERROR, message);
    }

    public static void warn(String message) {
        print(Level.WARN, message);
    }

    public static void debug(String message) {
        print(Level.DEBUG, message);
    }

    private static void print(Level level, String message) {
        int appLevel = Application.getInstance().mode.level;
        switch (level) {
            case DEBUG:
                if (appLevel >= Level.DEBUG.level)
                    getInstance().logger.debug(message);
                break;
            case INFO:
                if (appLevel >= Level.INFO.level)
                    getInstance().logger.info(message);
                break;
            case ERROR:
                if (appLevel >= Level.ERROR.level)
                    getInstance().logger.error(message);
                break;
            case WARN:
                if (appLevel >= Level.WARN.level)
                    getInstance().logger.warn(message);
                break;
        }
    }

    public enum Level {
        DEBUG(3),
        INFO(2),
        WARN(1),
        ERROR(0);

        private final int level;

        Level(int level) {
            this.level = level;
        }
    }
}
