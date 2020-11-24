package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AdvertResponseObj implements Serializable {

    private static final long serialVersionUID = -4623796631862192959L;
    private String title;
    private String description;
    private Double openingBid;
    private String startDate;
    private String endDate;
    private Integer itemId;
    private String canonicalURL;
    private Integer categoryId;
    private Integer memberId;
    private String condition;
    private Set<String> brands;
    private Set<String> sizes;
    private Set<String> colors;
    private String allowedBuyerRegion;
    private boolean shipsToBuyer;
    private boolean isAuction;
    private List<byte[]> images;

    public AdvertResponseObj() {
    }

    public AdvertResponseObj(String title, String description, Double openingBid, String startDate, String endDate,
            Integer itemId, String canonicalURL, Integer categoryId, Integer memberId, String condition,
            Set<String> brands, Set<String> sizes, Set<String> colors, String allowedBuyerRegion, boolean shipsToBuyer,
            boolean isAuction, List<byte[]> images) {
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOpeningBid() {
        return this.openingBid;
    }

    public void setOpeningBid(Double openingBid) {
        this.openingBid = openingBid;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getCanonicalURL() {
        return this.canonicalURL;
    }

    public void setCanonicalURL(String canonicalURL) {
        this.canonicalURL = canonicalURL;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Set<String> getBrands() {
        return this.brands;
    }

    public void setBrands(Set<String> brands) {
        this.brands = brands;
    }

    public Set<String> getSizes() {
        return this.sizes;
    }

    public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
    }

    public Set<String> getColors() {
        return this.colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public String getAllowedBuyerRegion() {
        return this.allowedBuyerRegion;
    }

    public void setAllowedBuyerRegion(String allowedBuyerRegion) {
        this.allowedBuyerRegion = allowedBuyerRegion;
    }

    public boolean getShipsToBuyer() {
        return this.shipsToBuyer;
    }

    public void setShipsToBuyer(boolean shipsToBuyer) {
        this.shipsToBuyer = shipsToBuyer;
    }

    public boolean isAuction() {
        return this.isAuction;
    }

    public void setAuction(boolean isAuction) {
        this.isAuction = isAuction;
    }

    public List<byte[]> getImages() {
        return this.images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AdvertResponseObj)) {
            return false;
        }
        AdvertResponseObj advertResponseObj = (AdvertResponseObj) o;
        return Objects.equals(title, advertResponseObj.title)
                && Objects.equals(description, advertResponseObj.description)
                && openingBid == advertResponseObj.openingBid && Objects.equals(startDate, advertResponseObj.startDate)
                && Objects.equals(endDate, advertResponseObj.endDate)
                && Objects.equals(itemId, advertResponseObj.itemId)
                && Objects.equals(canonicalURL, advertResponseObj.canonicalURL)
                && Objects.equals(categoryId, advertResponseObj.categoryId)
                && Objects.equals(memberId, advertResponseObj.memberId)
                && Objects.equals(condition, advertResponseObj.condition)
                && Objects.equals(brands, advertResponseObj.brands) && Objects.equals(sizes, advertResponseObj.sizes)
                && Objects.equals(colors, advertResponseObj.colors)
                && Objects.equals(allowedBuyerRegion, advertResponseObj.allowedBuyerRegion)
                && shipsToBuyer == advertResponseObj.shipsToBuyer && isAuction == advertResponseObj.isAuction
                && Objects.equals(images, advertResponseObj.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, openingBid, startDate, endDate, itemId, canonicalURL, categoryId,
                memberId, condition, brands, sizes, colors, allowedBuyerRegion, shipsToBuyer, isAuction, images);
    }

    @Override
    public String toString() {
        return "{" + " title='" + getTitle() + "'" + ", description='" + getDescription() + "'" + ", openingBid='"
                + getOpeningBid() + "'" + ", startDate='" + getStartDate() + "'" + ", endDate='" + getEndDate() + "'"
                + ", itemId='" + getItemId() + "'" + ", canonicalURL='" + getCanonicalURL() + "'" + ", categoryId='"
                + getCategoryId() + "'" + ", memberId='" + getMemberId() + "'" + ", condition='" + getCondition() + "'"
                + ", brands='" + getBrands() + "'" + ", sizes='" + getSizes() + "'" + ", colors='" + getColors() + "'"
                + ", allowedBuyerRegion='" + getAllowedBuyerRegion() + "'" + ", shipsToBuyer='" + getShipsToBuyer()
                + "'" + ", isAuction='" + isAuction() + "'" + ", images='" + getImages() + "'" + "}";
    }

    public Advert build() {
        Set<String> imgNames = new HashSet<>();

        getImages().forEach(img -> imgNames.add(String.valueOf(getItemId()).concat("-")
                .concat(String.valueOf(getImages().indexOf(img)).concat(".jpg"))));

        return new Advert(getTitle(), getDescription(), getOpeningBid(), getStartDate(), getEndDate(), getItemId(),
                getCanonicalURL(), getCategoryId(), getMemberId(), getCondition(), getBrands(), getSizes(), getColors(),
                getAllowedBuyerRegion(), getShipsToBuyer(), isAuction(), imgNames);
    }

}
