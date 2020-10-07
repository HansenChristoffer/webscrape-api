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

@Entity
public class Category implements Serializable {

    public Category() {}

    public Category(String name, String categoryHref, Collection<Advert> adverts) {
        this.name = name;
        this.categoryHref = categoryHref;
        this.adverts = adverts;
    }

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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public String getCategoryHref() {
        return categoryHref;
    }

    public void setCategoryHref(String categoryHref) {
        this.categoryHref = categoryHref;
    }

    public Collection<Advert> getAdverts() {
        return adverts;
    }

    public void setCollections(Collection<Advert> adverts) {
        this.adverts = adverts;
    }
}
