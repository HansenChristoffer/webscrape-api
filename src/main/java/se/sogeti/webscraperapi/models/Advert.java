package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

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

@Document(collection = "scrapes_adverts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "title", "description", "openingBid", "startDate", "endDate", "itemId", "canonicalURL",
        "categoryId", "memberId", "condition", "brands", "sizes", "colors", "allowedBuyerRegion", "shipsToBuyer",
        "isAuction", "images", "createdDate", "lastModifiedDate" })
public class Advert implements Serializable {

    @JsonProperty("id")
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("openingBid")
    private Double openingBid;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("itemId")
    @Indexed(unique = true)
    private Integer itemId;

    @JsonProperty("canonicalURL")
    private String canonicalURL;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("memberId")
    private Integer memberId;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("brands")
    private Set<String> brands = null;

    @JsonProperty("sizes")
    private Set<String> sizes = null;

    @JsonProperty("colors")
    private Set<String> colors = null;

    @JsonProperty("allowedBuyerRegion")
    private String allowedBuyerRegion;

    @JsonProperty("shipsToBuyer")
    private Boolean shipsToBuyer;

    @JsonProperty("isAuction")
    private Boolean isAuction;

    @JsonProperty("images")
    private Set<String> images;

    @JsonProperty("createdDate")
    @CreatedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant createdDate;

    @JsonProperty("lastModifiedDate")
    @LastModifiedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant lastModifiedDate;

    private static final long serialVersionUID = 4321153223751586030L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Advert() {
    }

    /**
     *
     * @param id
     * @param images
     * @param endDate
     * @param canonicalURL
     * @param isAuction
     * @param description
     * @param allowedBuyerRegion
     * @param title
     * @param colors
     * @param itemId
     * @param openingBid
     * @param condition
     * @param sizes
     * @param shipsToBuyer
     * @param brands
     * @param startDate
     * @param categoryId
     * @param memberId
     */
    public Advert(String id, String title, String description, Double openingBid, String startDate, String endDate,
            Integer itemId, String canonicalURL, Integer categoryId, Integer memberId, String condition,
            Set<String> brands, Set<String> sizes, Set<String> colors, String allowedBuyerRegion, Boolean shipsToBuyer,
            Boolean isAuction, Set<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.openingBid = openingBid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemId = itemId;
        this.canonicalURL = canonicalURL;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.condition = condition;
        this.brands = brands;
        this.sizes = sizes;
        this.colors = colors;
        this.allowedBuyerRegion = allowedBuyerRegion;
        this.shipsToBuyer = shipsToBuyer;
        this.isAuction = isAuction;
        this.images = images;
    }

    public Advert(String title, String description, Double openingBid, String startDate, String endDate, Integer itemId,
            String canonicalURL, Integer categoryId, Integer memberId, String condition, Set<String> brands,
            Set<String> sizes, Set<String> colors, String allowedBuyerRegion, Boolean shipsToBuyer, Boolean isAuction,
            Set<String> images) {
        this.title = title;
        this.description = description;
        this.openingBid = openingBid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemId = itemId;
        this.canonicalURL = canonicalURL;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.condition = condition;
        this.brands = brands;
        this.sizes = sizes;
        this.colors = colors;
        this.allowedBuyerRegion = allowedBuyerRegion;
        this.shipsToBuyer = shipsToBuyer;
        this.isAuction = isAuction;
        this.images = images;
    }

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("openingBid")
    public Double getOpeningBid() {
        return openingBid;
    }

    @JsonProperty("openingBid")
    public void setOpeningBid(Double openingBid) {
        this.openingBid = openingBid;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("itemId")
    public Integer getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("canonicalURL")
    public String getCanonicalURL() {
        return canonicalURL;
    }

    @JsonProperty("canonicalURL")
    public void setCanonicalURL(String canonicalURL) {
        this.canonicalURL = canonicalURL;
    }

    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("memberId")
    public Integer getMemberId() {
        return memberId;
    }

    @JsonProperty("memberId")
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @JsonProperty("condition")
    public String getCondition() {
        return condition;
    }

    @JsonProperty("condition")
    public void setCondition(String condition) {
        this.condition = condition;
    }

    @JsonProperty("brands")
    public Set<String> getBrands() {
        return brands;
    }

    @JsonProperty("brands")
    public void setBrands(Set<String> brands) {
        this.brands = brands;
    }

    @JsonProperty("sizes")
    public Set<String> getSizes() {
        return sizes;
    }

    @JsonProperty("sizes")
    public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
    }

    @JsonProperty("colors")
    public Set<String> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    @JsonProperty("allowedBuyerRegion")
    public String getAllowedBuyerRegion() {
        return allowedBuyerRegion;
    }

    @JsonProperty("allowedBuyerRegion")
    public void setAllowedBuyerRegion(String allowedBuyerRegion) {
        this.allowedBuyerRegion = allowedBuyerRegion;
    }

    @JsonProperty("shipsToBuyer")
    public Boolean getShipsToBuyer() {
        return shipsToBuyer;
    }

    @JsonProperty("shipsToBuyer")
    public void setShipsToBuyer(Boolean shipsToBuyer) {
        this.shipsToBuyer = shipsToBuyer;
    }

    @JsonProperty("isAuction")
    public Boolean isAuction() {
        return isAuction;
    }

    @JsonProperty("isAuction")
    public void setAuction(Boolean isAuction) {
        this.isAuction = isAuction;
    }

    @JsonProperty("images")
    public Set<String> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Set<String> images) {
        this.images = images;
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
        if (!(o instanceof Advert)) {
            return false;
        }
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) && Objects.equals(title, advert.title)
                && Objects.equals(description, advert.description) && Objects.equals(openingBid, advert.openingBid)
                && Objects.equals(startDate, advert.startDate) && Objects.equals(endDate, advert.endDate)
                && Objects.equals(itemId, advert.itemId) && Objects.equals(canonicalURL, advert.canonicalURL)
                && Objects.equals(categoryId, advert.categoryId) && Objects.equals(memberId, advert.memberId)
                && Objects.equals(condition, advert.condition) && Objects.equals(brands, advert.brands)
                && Objects.equals(sizes, advert.sizes) && Objects.equals(colors, advert.colors)
                && Objects.equals(allowedBuyerRegion, advert.allowedBuyerRegion)
                && Objects.equals(shipsToBuyer, advert.shipsToBuyer) && Objects.equals(isAuction, advert.isAuction)
                && Objects.equals(images, advert.images) && Objects.equals(createdDate, advert.createdDate)
                && Objects.equals(lastModifiedDate, advert.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, openingBid, startDate, endDate, itemId, canonicalURL, categoryId,
                memberId, condition, brands, sizes, colors, allowedBuyerRegion, shipsToBuyer, isAuction, images,
                createdDate, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", description='" + getDescription()
                + "'" + ", openingBid='" + getOpeningBid() + "'" + ", startDate='" + getStartDate() + "'"
                + ", endDate='" + getEndDate() + "'" + ", itemId='" + getItemId() + "'" + ", canonicalURL='"
                + getCanonicalURL() + "'" + ", categoryId='" + getCategoryId() + "'" + ", memberId='" + getMemberId()
                + "'" + ", condition='" + getCondition() + "'" + ", brand='" + getBrands() + "'" + ", sizes='"
                + getSizes() + "'" + ", colors='" + getColors() + "'" + ", allowedBuyerRegion='"
                + getAllowedBuyerRegion() + "'" + ", shipsToBuyer='" + getShipsToBuyer() + "'" + ", isAuction='"
                + isAuction() + "'" + ", images='" + getImages() + "'" + ", createdDate='" + getCreatedDate() + "'"
                + ", lastModifiedDate='" + getLastModifiedDate() + "'" + "}";
    }

}