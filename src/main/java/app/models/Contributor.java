package app.models;

public class Contributor {

    public String firstName;
    public String lastName;
    public String title;
    public String imageUrl;

    public Contributor(String firstName, String lastName, String title, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
