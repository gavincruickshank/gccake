package co.uk.gcsample.cakes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cake {

    @Id
    private String title;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("image")
    private String imageLocation;

    public Cake() {

    }

    public Cake(String title, String description, String imageLocation) {
        this.title = title;
        this.description = description;
        this.imageLocation = imageLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
