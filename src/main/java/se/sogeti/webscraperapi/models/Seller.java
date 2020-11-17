package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_sellers")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "memberId", "alias", "city", "canonicalURL", "sellerRatingAverage", "totalRating",
        "isCompany", "createdDate", "lastModifiedDate" })
public class Seller implements Serializable {

    @JsonProperty("id")
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @JsonProperty("memberId")
    @Indexed(unique = true)
    private Integer memberId;

    @JsonProperty("alias")
    @Indexed(unique = true)
    private String alias;

    @JsonProperty("city")
    private String city;

    @JsonProperty("canonicalURL")
    private String canonicalURL;

    @JsonProperty("sellerRatingAverage")
    private Double sellerRatingAverage;

    @JsonProperty("totalRating")
    private Integer totalRating;

    @JsonProperty("isCompany")
    private Boolean isCompany;

    @JsonProperty("createdDate")
    @CreatedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant createdDate;

    @JsonProperty("lastModifiedDate")
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

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("memberId")
    public Integer getMemberId() {
        return memberId;
    }

    @JsonProperty("memberId")
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("canonicalURL")
    public String getCanonicalURL() {
        return canonicalURL;
    }

    @JsonProperty("canonicalURL")
    public void setCanonicalURL(String canonicalURL) {
        this.canonicalURL = canonicalURL;
    }

    @JsonProperty("sellerRatingAverage")
    public Double getSellerRatingAverage() {
        return sellerRatingAverage;
    }

    @JsonProperty("sellerRatingAverage")
    public void setSellerRatingAverage(Double sellerRatingAverage) {
        this.sellerRatingAverage = sellerRatingAverage;
    }

    @JsonProperty("totalRating")
    public Integer getTotalRating() {
        return totalRating;
    }

    @JsonProperty("totalRating")
    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }

    @JsonProperty("isCompany")
    public Boolean isCompany() {
        return isCompany;
    }

    @JsonProperty("isCompany")
    public void setCompany(Boolean isCompany) {
        this.isCompany = isCompany;
    }

    @JsonProperty("createdDate")
    public Instant getCreatedDate() {
        return this.createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("lastModifiedDate")
    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    @JsonProperty("lastModifiedDate")
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