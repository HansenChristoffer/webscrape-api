package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scrapes_links")
public class Link implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -368561808660439434L;

    public Link() {}

    public Link(String href) {
        this.href = href;
        this.isOpen = true;
    }

    @Id
    private String id;

    @Indexed(unique = true)
    private String href;

    private boolean isOpen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
