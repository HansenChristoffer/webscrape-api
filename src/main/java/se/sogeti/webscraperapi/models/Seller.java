package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
