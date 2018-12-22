package uz.androidmk.fooddelivery.ui.checkout;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Azamat on 9/16/2018.
 */

public class PlaceInfo {

    String name;
    String placeId;
    String address;
    LatLng latLng;

    public PlaceInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public PlaceInfo(String name, String placeId, String address, LatLng latLng) {
        this.name = name;
        this.placeId = placeId;
        this.address = address;
        this.latLng = latLng;
    }
}
