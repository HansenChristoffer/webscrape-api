package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Seller implements Serializable {

    public Seller() {
    }

    public Seller(String name, String location, String registered, String sellerHref, Collection<Advert> adverts) {
        this.name = name;
        this.location = location;
        this.registered = registered;
        this.sellerHref = sellerHref;
        this.adverts = adverts;
    }

    /**
     *
     */
    private static final long serialVersionUID = -6703572985770238904L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "SELLER_NAME", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String name;

    @Column(name = "SELLER_LOCATION", columnDefinition = "VARCHAR(255)")
    private String location;

    @Column(name = "SELLER_REGISTERED_DATE", columnDefinition = "VARCHAR(255)")
    private String registered;

    @Column(name = "SELLER_HREF", columnDefinition = "VARCHAR(255)")
    private String sellerHref;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Advert> adverts;

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

    public Collection<Advert> getAdverts() {
        return adverts;
    }

    public void setCollections(Collection<Advert> adverts) {
        this.adverts = adverts;
    }
}
