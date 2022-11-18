package app.controllers;

import app.models.Contributor;
import app.repositories.ContributorsRepository;
import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.List;
import java.util.Map;

public class ContributorsController extends Controller {

    public void index(Response response) {
        List<Contributor> contributors = ContributorsRepository.getContributors();

        response.html(View.make(
                "contributors",
                Map.entry("contributors", contributors)
        ));
    }
}
