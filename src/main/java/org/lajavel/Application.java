package org.lajavel;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {
    private static Application instance;
    public Javalin server;
    public Integer port;

    public Mode mode;

    private Application(Integer port, Mode mode) {
        this.port = port;
        this.server = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(port);
        this.mode = mode;
    }

    public static Application start(Integer port, Mode mode) {
        if (instance == null) {
            instance = new Application(port, mode);
            Log.info("Application starting.");
            return instance;
        } else {
            throw new RuntimeException("Application already started");
        }
    }

    public static Application getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application not started");
        }
        return instance;
    }

    public enum Mode {
        DEVELOPMENT(3),
        TEST(1),
        PRODUCTION(0);
        public final int level;

        Mode(int level) {
            this.level = level;
        }
    }
}
