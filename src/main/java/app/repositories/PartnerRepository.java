package app.repositories;

import app.models.Partner;

import java.util.List;

public class PartnerRepository {
    public static List<Partner> findAll() {
        Partner[] partners = {
                new Partner("MyDigitalSchool", "s"),
                new Partner("Laravel", "a"),
                new Partner("Javalin", "d"),
                new Partner("Mark Zuck et Berg", "f"),
        };
        return List.of(partners);
    }

    public static List<List<Partner>> findAllAll() {
        Partner[] partners = {
                new Partner("MyDigitalSchool", "s"),
                new Partner("Laravel", "a"),
                new Partner("Javalin", "d"),
                new Partner("Mark Zuck et Berg", "f"),
        };
        List<Partner> partnersList = List.of(partners);
        return List.of(partnersList, partnersList, partnersList);
    }

}
