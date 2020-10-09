package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scrapes_adverts")
public class Advert implements Serializable {

    public Advert() {}

    public Advert(String name, Category category, Seller seller, String description, double price, String published,
            String objectNumber, String href) {
        this.name = name;
        this.category = category;
        this.seller = seller;
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

    private Category category; // TODO Make this reference as a String category name instead

    private Seller seller; // TODO Make this reference as a String seller name instead

    private String description;

    private double price;

    private String published;

    @Indexed(unique = true)
    private String objectNumber;

    private String href;

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    // public byte[] getImage() {
    // return image;
    // }

    // public void setImage() {
    // this.image = image;
    // }

}