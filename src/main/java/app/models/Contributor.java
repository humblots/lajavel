package app.models;

public class Contributor {

    public String firstName;
    public String lastName;
    public String title;
    public String imageUrl;
    public String link;

    public Contributor(String firstName, String lastName, String title, String imageUrl, String link) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.imageUrl = imageUrl;
        this.link = link;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
