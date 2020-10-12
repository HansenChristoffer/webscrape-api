package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_sellers")
public class Seller implements Serializable {

    public Seller() {
    }

    public Seller(String name, String location, String registered, String href) {
        this.name = name;
        this.location = location;
        this.registered = registered;
        this.href = href;
    }

    /**
     *
     */
    private static final long serialVersionUID = -6703572985770238904L;

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String location;

    private String registered;

    private String href;

    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Instant addedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Instant getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Instant addedDate) {
        this.addedDate = addedDate;
    }

}
