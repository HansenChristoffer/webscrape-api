package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "scrapes_links")
public class Link implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -368561808660439434L;

    public Link() {
    }

    public Link(String href) {
        this.href = href;
        this.isOpen = true;
    }

    @MongoId(value = FieldType.OBJECT_ID)
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

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("href", href).append("isOpen", isOpen).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(href).append(isOpen).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Link)) {
            return false;
        }
        Link rhs = ((Link) other);
        return new EqualsBuilder().append(id, rhs.id).append(href, rhs.href).append(isOpen, rhs.isOpen).isEquals();
    }
}
