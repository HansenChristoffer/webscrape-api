package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Advert implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1331200230166367593L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADVERT_CATEGORY", nullable = false)
    private Category category;

    @Column(name = "ADVERT_NAME", columnDefinition = "VARCHAR(255)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ADVERT_SELLER", nullable = false)
    private Seller seller;

    @Column(name = "ADVERT_DESCRIPTION", columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "ADVERT_PRICE", columnDefinition = "DOUBLE")
    private double price;

    @Column(name = "ADVERT_PUBLISHED", columnDefinition = "VARCHAR(255)")
    private String published;

    @Column(name = "ADVERT_OBJECT_NUMBER", columnDefinition = "VARCHAR(255)")
    private String objectNumber;

    @Column(name = "ADVERT_HREF", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String advertHref;

    //@Lob
    //@Column(name = "ADVERT_IMAGE", columnDefinition = "BLOB")
    //private byte[] image; // TODO Research and find solution on how or if you should store image in database
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAdvertHref() {
        return advertHref;
    }

    public void setAdvertHref(String advertHref) {
        this.advertHref = advertHref;
    }

    // public byte[] getImage() {
    //     return image;
    // }

    // public void setImage() {
    //     this.image = image;
    // }

}