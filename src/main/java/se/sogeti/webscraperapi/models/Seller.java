package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SELLER_NAME", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String name;

    @Column(name = "SELLER_LOCATION", columnDefinition = "VARCHAR(255)")
    private String location;

    @Column(name = "SELLER_REGISTERED_DATE", columnDefinition = "VARCHAR(255)")
    private String registered;

    @Column(name = "SELLER_HREF", columnDefinition = "VARCHAR(255)")
    private String sellerHref;

    // @Column(name = "seller_adverts")
    // private Collections<Advert> adverts;

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

    public String getSellerHref() {
        return sellerHref;
    }

    public void setSellerHref(String sellerHref) {
        this.sellerHref = sellerHref;
    }

    // public Collections<Advert> getAdverts() {
    // return adverts;
    // }

    // public void setCollections(Collections<Advert> adverts) {
    // this.adverts = adverts;
    // }
}
