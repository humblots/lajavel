package app.repositories;

import app.models.Partner;

import java.util.List;

public class PartnersRepository {
    private static PartnersRepository instance;
    private List<Partner> data;

    private PartnersRepository() {
        Partner[] partners = {
                new Partner(
                        "MyDigitalSchool",
                        "https://file.diplomeo-static.com/file/00/00/01/23/12398.svg",
                        "https://www.mydigitalschool.com/"
                ),
                new Partner(
                        "Laravel",
                        "https://itanea.fr/apprendre-le-developpement-web/wp-content/" +
                                "uploads/2020/07/laravel-mark-red-type-black_w1280.png",
                        "https://laravel.com/"
                ),
                new Partner(
                        "Javalin",
                        "https://img.stackshare.io/service/7031/favicon.png",
                        "https://javalin.io/"
                ),
        };
        this.data = List.of(partners);
    }

    public static PartnersRepository getInstance() {
        if (instance == null) {
            instance = new PartnersRepository();
        }
        return instance;
    }

    public static Partner getOneFromName(String name) {
        return PartnersRepository.getInstance().data.stream()
                .filter(partner -> partner.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Partner> getPartners() {
        return PartnersRepository.getInstance().data;
    }

}
