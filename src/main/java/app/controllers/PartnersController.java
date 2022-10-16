package app.controllers;

import app.models.Partner;
import app.repositories.PartnerRepository;
import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.List;
import java.util.Map;

public class PartnersController extends Controller {
    public void index(Response response) {
        List<Partner> partnerList = PartnerRepository.findAll();
        List<List<Partner>> partnersPartnersList = PartnerRepository.findAllAll();

        Partner partner = new Partner("Lajavel", "https://lajavel");
        response.html(View.make(
                "partners",
                Map.entry("partner", partner),
                Map.entry("partners", partnerList),
                Map.entry("partnersList", partnersPartnersList)
        ));
    }
}
