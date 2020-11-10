package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_adverts")
public class Advert implements Serializable {

    public Advert() {
    }

    public Advert(String name, String categoryName, String sellerName, String description, double price,
            String published, String objectNumber, String href, String condition, String brand, String size,
            String color, List<String> imageNames, String advertPageImageName) {
        this.name = name;
        this.categoryName = categoryName;
        this.sellerName = sellerName;
        this.description = description;
        this.price = price;
        this.published = published;
        this.objectNumber = objectNumber;
        this.href = href;
        this.condition = condition;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.imageNames = imageNames;
        this.advertPageImageName = advertPageImageName;
    }

    private static final long serialVersionUID = 1331200230166367593L;

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String name;

    private String categoryName;

    private String sellerName;

    private String description;

    private double price = 0.0;

    private String published;

    @Indexed(unique = true)
    private String objectNumber;

    private String href;

    private String condition;

    private String brand;

    private String size;

    private String color;

    private List<String> imageNames;

    private String advertPageImageName;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Instant getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Instant addedDate) {
        this.addedDate = addedDate;
    }

    public List<String> getImage() {
        return imageNames;
    }

    public void setImage(List<String> imageNames) {
        this.imageNames = imageNames;
    }

    public String getAdvertPageImageName() {
        return advertPageImageName;
    }

    public void setAdvertPageImageName(String advertPageImageName) {
        this.advertPageImageName = advertPageImageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("description", description)
                .append("Condition", condition).append("brand", brand).append("size", size).append("color", color)
                .append("price", price).append("published", published).append("objectNumber", objectNumber)
                .append("href", href).append("categoryName", categoryName).append("sellerName", sellerName)
                .append("imageNames", imageNames).append("advertPageImageName", advertPageImageName).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sellerName).append(price).append(name).append(objectNumber)
                .append(description).append(condition).append(color).append(size).append(brand).append(id)
                .append(published).append(href).append(categoryName).append(sellerName).append(imageNames)
                .append(advertPageImageName).toHashCode();
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
                .append(condition, rhs.condition).append(brand, rhs.brand).append(size, rhs.size)
                .append(color, rhs.color).append(imageNames, rhs.imageNames)
                .append(advertPageImageName, rhs.advertPageImageName).isEquals();
    }

}