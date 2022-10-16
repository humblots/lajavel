package app.controllers;

import app.models.Contributor;
import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.Map;

public class ContributorsController extends Controller {

    public void index(Response response) {
        Contributor contributor1 = new Contributor("John", "Doe", "Developer", "https://lajavel");

        response.html(View.make(
                "contributors",
                Map.entry("contributor", contributor1)
        ));
    }
}
