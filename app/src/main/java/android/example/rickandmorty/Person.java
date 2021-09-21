package android.example.rickandmorty;

public class Person {
    private String name;
    private String status;
    private String species;
    private String gender;
    private String location;
    private String image;

    public Person(String name, String status, String species, String gender, String location, String image) {
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.location = location;
        this.image = image;
    }

    public Person() {
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }
}
