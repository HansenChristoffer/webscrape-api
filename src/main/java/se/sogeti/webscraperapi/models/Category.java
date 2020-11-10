package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_categories")
public class Category implements Serializable {

    public Category() {
    }

    public Category(String name, String href, boolean isOpen) {
        this.name = name;
        this.href = href;
        this.isOpen = isOpen;
    }

    /**
     *
     */
    private static final long serialVersionUID = -2371741008467000093L;

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String name;

    @Indexed(unique = true)
    private String href;

    private boolean isOpen = true;

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

    public Instant getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Instant addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("href", href)
                .append("isOpen", isOpen).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(id).append(href).append(isOpen).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Category)) {
            return false;
        }
        Category rhs = ((Category) other);
        return new EqualsBuilder().append(name, rhs.name).append(id, rhs.id).append(href, rhs.href)
                .append(isOpen, rhs.isOpen).isEquals();
    }
}
