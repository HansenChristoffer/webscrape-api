package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scrapes_categories")
public class Category implements Serializable {

    public Category() {}

    public Category(String name, String href, Collection<String> adverts) {
        this.name = name;
        this.href = href;
        this.adverts = adverts;
    }

    /**
     *
     */
    private static final long serialVersionUID = -2371741008467000093L;

    @Id
    private String id;

    private String name; // TODO Make this unique

    private String href;

    private Collection<String> adverts;

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Collection<String> getAdverts() {
        return adverts;
    }

    public void setAdverts(Collection<String> adverts) {
        this.adverts = adverts;
    }
}
