package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "scrapes_adverts")
public class Advert implements Serializable {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String title;

    private String description;

    private Double openingBid;

    private String startDate;

    private String endDate;

    @Indexed(unique = true)
    private Integer itemId;

    private String canonicalURL;

    private Integer categoryId;

    private Integer memberId;

    private String condition;

    private Set<String> brands = null;

    private Set<String> sizes = null;

    private Set<String> colors = null;

    private String allowedBuyerRegion;

    private Boolean shipsToBuyer;

    private Boolean isAuction;

    private Set<String> images;

    @CreatedDate
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Instant createdDate;

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOpeningBid() {
        return openingBid;
    }

    public void setOpeningBid(Double openingBid) {
        this.openingBid = openingBid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getCanonicalURL() {
        return canonicalURL;
    }

    public void setCanonicalURL(String canonicalURL) {
        this.canonicalURL = canonicalURL;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Set<String> getBrands() {
        return brands;
    }

    public void setBrands(Set<String> brands) {
        this.brands = brands;
    }

    public Set<String> getSizes() {
        return sizes;
    }

    public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
    }

    public Set<String> getColors() {
        return colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public String getAllowedBuyerRegion() {
        return allowedBuyerRegion;
    }

    public void setAllowedBuyerRegion(String allowedBuyerRegion) {
        this.allowedBuyerRegion = allowedBuyerRegion;
    }

    public Boolean getShipsToBuyer() {
        return shipsToBuyer;
    }

    public void setShipsToBuyer(Boolean shipsToBuyer) {
        this.shipsToBuyer = shipsToBuyer;
    }

    public Boolean isAuction() {
        return isAuction;
    }

    public void setAuction(Boolean isAuction) {
        this.isAuction = isAuction;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
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

    public AdvertResponseObj build() {
        return new AdvertResponseObj(getTitle(), getDescription(), getOpeningBid(), getStartDate(), getEndDate(),
                getItemId(), getCanonicalURL(), getCategoryId(), getMemberId(), getCondition(), getBrands(), getSizes(),
                getColors(), getAllowedBuyerRegion(), getShipsToBuyer(), isAuction(), new ArrayList<>());
    }
}