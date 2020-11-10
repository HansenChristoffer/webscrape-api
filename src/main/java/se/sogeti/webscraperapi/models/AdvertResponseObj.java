package se.sogeti.webscraperapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdvertResponseObj implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private double price;

    private String published;

    private String objectNumber;

    private String href;

    private String categoryName;

    private String sellerName;

    private String condition;

    private String brand;

    private String size;

    private String color;

    private List<byte[]> images;

    private byte[] advertPageImage;

    public AdvertResponseObj() {
    }

    public AdvertResponseObj(String name, String description, double price, String published, String objectNumber,
            String href, String categoryName, String sellerName, String condition, String brand, String size,
            String color, List<byte[]> images, byte[] advertPageImage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.published = published;
        this.objectNumber = objectNumber;
        this.href = href;
        this.categoryName = categoryName;
        this.sellerName = sellerName;
        this.condition = condition;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.images = images;
        this.advertPageImage = advertPageImage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublished() {
        return this.published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getObjectNumber() {
        return this.objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<byte[]> getImages() {
        return this.images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public byte[] getAdvertPageImage() {
        return this.advertPageImage;
    }

    public void setAdvertPageImage(byte[] advertPageImage) {
        this.advertPageImage = advertPageImage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AdvertResponseObj)) {
            return false;
        }
        AdvertResponseObj advertResponseObj = (AdvertResponseObj) o;
        return Objects.equals(name, advertResponseObj.name)
                && Objects.equals(description, advertResponseObj.description) && price == advertResponseObj.price
                && Objects.equals(published, advertResponseObj.published)
                && Objects.equals(objectNumber, advertResponseObj.objectNumber)
                && Objects.equals(href, advertResponseObj.href)
                && Objects.equals(categoryName, advertResponseObj.categoryName)
                && Objects.equals(sellerName, advertResponseObj.sellerName)
                && Objects.equals(condition, advertResponseObj.condition)
                && Objects.equals(brand, advertResponseObj.brand) && Objects.equals(size, advertResponseObj.size)
                && Objects.equals(color, advertResponseObj.color) && Objects.equals(images, advertResponseObj.images)
                && Objects.equals(advertPageImage, advertResponseObj.advertPageImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, published, objectNumber, href, categoryName, sellerName,
                condition, brand, size, color, images, advertPageImage);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", description='" + getDescription() + "'" + ", price='" + getPrice()
                + "'" + ", published='" + getPublished() + "'" + ", objectNumber='" + getObjectNumber() + "'"
                + ", href='" + getHref() + "'" + ", categoryName='" + getCategoryName() + "'" + ", sellerName='"
                + getSellerName() + "'" + ", condition='" + getCondition() + "'" + ", brand='" + getBrand() + "'"
                + ", size='" + getSize() + "'" + ", color='" + getColor() + "'" + ", images='" + getImages() + "'"
                + ", advertPageImage='" + getAdvertPageImage() + "'" + "}";
    }

    public Advert build() {
        List<String> imgNames = new ArrayList<>();

        getImages().forEach(img -> imgNames
                .add(getObjectNumber().concat("-").concat(String.valueOf(getImages().indexOf(img)).concat(".jpg"))));

        return new Advert(getName(), getCategoryName(), getSellerName(), getDescription(), getPrice(), getPublished(),
                getObjectNumber(), getHref(), getCondition(), getBrand(), getSize(), getColor(), imgNames,
                (getObjectNumber().concat("-").concat("advertPageImage").concat(".jpg")));
    }

}
