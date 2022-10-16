package app.repositories;

import app.models.Partner;

import java.util.List;

public class PartnerRepository {
    public static List<Partner> findAll() {
        Partner[] partners = {
                new Partner("MyDigitalSchool", "https://file.diplomeo-static.com/file/00/00/01/23/12398.svg"),
                new Partner("Laravel", "https://itanea.fr/apprendre-le-developpement-web/wp-content/uploads/2020/07/laravel-mark-red-type-black_w1280.png"),
                new Partner("Javalin", "https://javalin.io/img/javalin.png"),
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
