package com.medum.medum.model;

/**
 * Created by medum on 8/1/17.
 */

public class Picture {
    private String picture;
    private String titlecard;
    private String pricecard;
    private String descriptioncard;
    private String timecard = "0";

    public Picture(String picture, String titlecard, String pricecard, String descriptioncard, String timecard) {
        this.picture = picture;
        this.titlecard = titlecard;
        this.pricecard = pricecard;
        this.descriptioncard = descriptioncard;
        this.timecard = timecard;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitlecard() {
        return titlecard;
    }

    public void setTitlecard(String titlecard) {
        this.titlecard = titlecard;
    }

    public String getPricecard() {
        return pricecard;
    }

    public void setPricecard(String pricecard) {
        this.pricecard = pricecard;
    }

    public String getDescriptioncard() {
        return descriptioncard;
    }

    public void setDescriptioncard(String descriptioncard) {
        this.descriptioncard = descriptioncard;
    }

    public String getTimecard() {
        return timecard;
    }

    public void setTimecard(String timecard) {
        this.timecard = timecard;
    }
}
