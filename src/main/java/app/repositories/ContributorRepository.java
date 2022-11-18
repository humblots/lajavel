package app.repositories;

import app.models.Contributor;

import java.util.List;

public class ContributorRepository {
    private static ContributorRepository instance;
    private List<Contributor> data;

    private ContributorRepository() {
        Contributor contributor = new Contributor(
                "Damien",
                "Dabernat",
                "Lead Developer",
                "https://avatars.githubusercontent.com/u/10465660?v=4",
                "https://github.com/DamienDabernat"
        );
        Contributor contributor2 = new Contributor(
                "St&eacute;phane",
                "Humblot",
                "Developer",
                "https://media-exp1.licdn.com/dms/image/D4E03AQEqpgOhywBo0w/profile-displ" +
                        "ayphoto-shrink_800_800/0/1666794455902?e=1672272000&v=beta&t=-DkXmlZ1kTo2kv" +
                        "AvML_BDyiTLHSt9YrH6MrrdwKYPiQ",
                "https://github.com/humblots"
        );
        this.data = List.of(contributor, contributor2);
    }

    public static ContributorRepository getInstance() {
        if (instance == null) {
            instance = new ContributorRepository();
        }
        return instance;
    }

    public static Contributor getOneFromFullName(String name) {
        return ContributorRepository.getInstance().data.stream()
                .filter(contributor -> contributor.getFullName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Contributor> getContributors() {
        return ContributorRepository.getInstance().data;
    }

}
