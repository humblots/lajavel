package app.repositories;

import app.models.Contributor;

import java.util.List;

public class ContributorRepository {
    public static List<Contributor> findAll() {
        Contributor[] contributors = {
                new Contributor(
                        "Damien", "Dabernat",
                        "Lead Developer", "https://avatars.githubusercontent.com/u/10465660?v=4"
                ),
                new Contributor("St&eacute;phane", "Humblot",
                        "Developer", "https://media-exp1.licdn.com/dms/image/D4D03AQGPbUCakZI61w/profile-displayphoto-shrink_800_800/0/1665691274546?e=1671667200&v=beta&t=sxGwAFWMcUxwRyt_FjuOrDEMzs4583Qrb6s7kaRF92Y"
                )
        };
        return List.of(contributors);
    }
}
