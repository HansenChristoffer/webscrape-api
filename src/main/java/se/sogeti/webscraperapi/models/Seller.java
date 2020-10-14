package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
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

    @DateTimeFormat(iso = ISO.DATE_TIME)
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("location", location)
                .append("registered", registered).append("href", href).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(registered).append(location).append(id).append(href)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Seller) == false) {
            return false;
        }
        Seller rhs = ((Seller) other);
        return new EqualsBuilder().append(name, rhs.name).append(registered, rhs.registered)
                .append(location, rhs.location).append(id, rhs.id).append(href, rhs.href).isEquals();
    }
}
