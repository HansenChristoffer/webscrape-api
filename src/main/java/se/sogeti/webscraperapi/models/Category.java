package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Category implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2371741008467000093L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CATEGORY_NAME", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String name;

    @Column(name = "CATEGORY_HREF", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String categoryHref;

    //@Column(name = "category_adverts")
    //private Collections<Advert> adverts; // TODO Fix OneToMany relationship (Perhaps JoinColumn)

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

    public String getCategoryHref() {
        return categoryHref;
    }

    public void setCategoryHref(String categoryHref) {
        this.categoryHref = categoryHref;
    }

    // public Collections<Advert> getAdverts() {
    //     return adverts;
    // }

    // public void setCollections(Collections<Advert> adverts) {
    //     this.adverts = adverts;
    // }
}
