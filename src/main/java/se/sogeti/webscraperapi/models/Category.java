package se.sogeti.webscraperapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_name", columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String name;

    @Column(name = "category_href", columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String categoryHref;

    //@Column(name = "category_adverts")
    //private Collections<Advert> adverts;

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
}
