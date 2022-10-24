package app;

import app.controllers.ContactController;
import app.controllers.ContributorsController;
import app.controllers.DefaultController;
import app.controllers.PartnersController;
import org.lajavel.Application;
import org.lajavel.HttpVerb;
import org.lajavel.Route;

public class MyApp {
    public static void main(String[] args) {
        Application app = Application.start(7070, Application.Mode.DEVELOPMENT);

        Route.register(HttpVerb.GET, "/", DefaultController.class, "index");
        Route.register(HttpVerb.GET, "/index", DefaultController.class, "index");
        Route.register(HttpVerb.GET, "/partners", PartnersController.class, "index");
        Route.register(HttpVerb.GET, "/contributors", ContributorsController.class, "index");
        Route.register(HttpVerb.GET, "/contact", ContactController.class, "index");
    }
}