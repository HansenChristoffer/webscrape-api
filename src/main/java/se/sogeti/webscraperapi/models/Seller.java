package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_sellers")
public class Seller implements Serializable {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @Indexed(unique = true)
    private Integer memberId;

    @Indexed(unique = true)
    private String alias;

    private String city;

    private String canonicalURL;

    private Double sellerRatingAverage;

    private Integer totalRating;

    private Boolean isCompany;

    @CreatedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant createdDate;

    @LastModifiedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant lastModifiedDate;

    private static final long serialVersionUID = -676623289604185577L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Seller() {
    }

    /**
     *
     * @param id
     * @param city
     * @param canonicalURL
     * @param alias
     * @param totalRating
     * @param id
     * @param isCompany
     * @param memberId
     * @param sellerRatingAverage
     */
    public Seller(String id, Integer memberId, String alias, String city, String canonicalURL,
            Double sellerRatingAverage, Integer totalRating, Boolean isCompany) {
        super();
        this.id = id;
        this.memberId = memberId;
        this.alias = alias;
        this.city = city;
        this.canonicalURL = canonicalURL;
        this.sellerRatingAverage = sellerRatingAverage;
        this.totalRating = totalRating;
        this.isCompany = isCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCanonicalURL() {
        return canonicalURL;
    }

    public void setCanonicalURL(String canonicalURL) {
        this.canonicalURL = canonicalURL;
    }

    public Double getSellerRatingAverage() {
        return sellerRatingAverage;
    }

    public void setSellerRatingAverage(Double sellerRatingAverage) {
        this.sellerRatingAverage = sellerRatingAverage;
    }

    public Integer getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }

    public Boolean isCompany() {
        return isCompany;
    }

    public void setCompany(Boolean isCompany) {
        this.isCompany = isCompany;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seller)) {
            return false;
        }
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id) && Objects.equals(memberId, seller.memberId)
                && Objects.equals(alias, seller.alias) && Objects.equals(city, seller.city)
                && Objects.equals(canonicalURL, seller.canonicalURL)
                && Objects.equals(sellerRatingAverage, seller.sellerRatingAverage)
                && Objects.equals(totalRating, seller.totalRating) && Objects.equals(isCompany, seller.isCompany)
                && Objects.equals(createdDate, seller.createdDate)
                && Objects.equals(lastModifiedDate, seller.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, alias, city, canonicalURL, sellerRatingAverage, totalRating, isCompany,
                createdDate, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", memberId='" + getMemberId() + "'" + ", alias='" + getAlias() + "'"
                + ", city='" + getCity() + "'" + ", canonicalURL='" + getCanonicalURL() + "'"
                + ", sellerRatingAverage='" + getSellerRatingAverage() + "'" + ", totalRating='" + getTotalRating()
                + "'" + ", isCompany='" + isCompany() + "'" + ", createdDate='" + getCreatedDate() + "'"
                + ", lastModifiedDate='" + getLastModifiedDate() + "'" + "}";
    }

}