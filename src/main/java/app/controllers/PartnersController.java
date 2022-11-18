package app.controllers;

import app.models.Partner;
import app.repositories.PartnersRepository;
import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.List;
import java.util.Map;

public class PartnersController extends Controller {
    public void index(Response response) {
        List<Partner> partners = PartnersRepository.getPartners();

        response.html(View.make(
                "partners",
                Map.entry("partners", partners)
        ));
    }
}
