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

@Document(collection = "scrapes_adverts")
public class Advert implements Serializable {

    public Advert() {
    }

    public Advert(String name, String categoryName, String sellerName, String description, double price,
            String published, String objectNumber, String href) {
        this.name = name;
        this.categoryName = categoryName;
        this.sellerName = sellerName;
        this.description = description;
        this.price = price;
        this.published = published;
        this.objectNumber = objectNumber;
        this.href = href;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1331200230166367593L;

    @Id
    private String id;

    private String name;

    private String categoryName;

    private String sellerName;

    private String description;

    private double price;

    private String published;

    @Indexed(unique = true)
    private String objectNumber;

    private String href;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant addedDate;

    // @Lob
    // @Column(name = "ADVERT_IMAGE", columnDefinition = "BLOB")
    // private byte[] image; // TODO Research and find solution on how or if you
    // should store image in database

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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
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

    // public byte[] getImage() {
    // return image;
    // }

    // public void setImage() {
    // this.image = image;
    // }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("categoryName", categoryName)
                .append("sellerName", sellerName).append("description", description).append("price", price)
                .append("published", published).append("objectNumber", objectNumber).append("href", href).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(name).append(sellerName).append(objectNumber)
                .append(description).append(id).append(published).append(href).append(categoryName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Advert)) {
            return false;
        }
        Advert rhs = ((Advert) other);
        return new EqualsBuilder().append(price, rhs.price).append(name, rhs.name).append(sellerName, rhs.sellerName)
                .append(objectNumber, rhs.objectNumber).append(description, rhs.description).append(id, rhs.id)
                .append(published, rhs.published).append(href, rhs.href).append(categoryName, rhs.categoryName)
                .isEquals();
    }

}