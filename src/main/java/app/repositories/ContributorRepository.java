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
                        "Developer", "https://media-exp1.licdn.com/dms/image/D4E03AQEqpgOhywBo0w/profile-displayphoto-shrink_800_800/0/1666794455902?e=1672272000&v=beta&t=-DkXmlZ1kTo2kvAvML_BDyiTLHSt9YrH6MrrdwKYPiQ"
                )
        };
        return List.of(contributors);
    }
}
