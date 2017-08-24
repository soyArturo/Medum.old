package com.medum.medum.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

/**
 * Created by medum on 8/20/17.
 */

public class House {
    private String userId;
    private String houseId;
    private String houseTitle;
    private String houseType;
    private String postType;
    private String descriptionPost;
    private String directionPost;
    private String pricePost;

    public House() {

    }

    public House(String userId, String houseId, String houseTitle, String houseType, String postType, String descriptionPost, String directionPost, String pricePost) {
        this.userId=userId;
        this.houseId = houseId;
        this.houseTitle = houseTitle;
        this.houseType = houseType;
        this.postType = postType;
        this.descriptionPost = descriptionPost;
        this.directionPost = directionPost;
        this.pricePost = pricePost;


    }

    public  String getUserId(){
        return userId;
    }

    public String getHouseId() {
        return houseId;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public String getHouseType() {
        return houseType;
    }

    public String getPostType() {
        return postType;
    }

    public String getDescriptionPost() {
        return descriptionPost;
    }

    public String getDirectionPost() {
        return directionPost;
    }

    public String getPricePost() {
        return pricePost;
    }


}
