package com.makarand.Model;

public class Shop {
    String id, latitude, longitude, references, reviews, shopname;

    public Shop() {
    }

    public Shop(String id, String latitude, String longitude, String references, String reviews, String shopname) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.references = references;
        this.reviews = reviews;
        this.shopname = shopname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
