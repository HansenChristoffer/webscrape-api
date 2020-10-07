package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Seller implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6703572985770238904L;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SELLER_ID")
    private Collection<Advert> adverts; // TODO Fix OneToMany relationship (Perhaps JoinColumn)

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
