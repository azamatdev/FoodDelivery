package uz.androidmk.fooddelivery.data.db.model;

import java.util.List;

/**
 * Created by Azamat on 10/27/2018.
 */

public class Order {

    String name;
    String tel_number;
    String location;
    Double latitude;
    Double longitude;
    String date;
    List<Food> foods;
    String userId;
    DeliveryState deliveryState;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Order(){

    }
//
//    public Order(String name, String tel_number, String location, Double latitude, Double longitude, String date, List<Food> foods, DeliveryState deliveryState, String userId) {
//        this.name = name;
//        this.tel_number = tel_number;
//        this.location = location;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.date = date;
//        this.foods = foods;
//        this.deliveryState = deliveryState;
//        this.userId = userId;
//    }

    public DeliveryState getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(DeliveryState deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }




}
