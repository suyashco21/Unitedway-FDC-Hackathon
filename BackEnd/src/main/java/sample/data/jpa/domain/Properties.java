package sample.data.jpa.domain;

/**
 * Created by anoopagarwal on 10/19/16.
 */
public class Properties {

    private String Name;
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
