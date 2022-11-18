package app.repositories;

import app.models.Partner;

import java.util.List;

public class PartnerRepository {
    private static PartnerRepository instance;
    private List<Partner> data;

    private PartnerRepository() {
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
                        ""
                ),
                new Partner("Javalin", "https://img.stackshare.io/service/7031/favicon.png", ""),
        };
        this.data = List.of(partners);
    }

    public static PartnerRepository getInstance() {
        if (instance == null) {
            instance = new PartnerRepository();
        }
        return instance;
    }
    
    public static Partner getOneFromName(String name) {
        return PartnerRepository.getInstance().data.stream()
                .filter(partner -> partner.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Partner> getPartners() {
        return PartnerRepository.getInstance().data;
    }

}
