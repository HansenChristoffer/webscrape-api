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

    @Column(name = "seller_name", columnDefinition = "varchar(255) ", nullable = false, unique = true)
    private String name;

    @Column(name = "seller_location", columnDefinition = "varchar(255)")
    private String location;

    @Column(name = "seller_registered_date", columnDefinition = "varchar(255)")
    private String registered;
    
    @Column(name = "seller_href", columnDefinition = "varchar(255)")
    private String sellerHref;

    //@Column(name = "seller_adverts")
    //private List<Advert> adverts;

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
}
