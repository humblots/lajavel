package app.controllers;

import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

public class DefaultController extends Controller {
    public void index(Response response) {
        response.html(View.make("index"));
    }
}