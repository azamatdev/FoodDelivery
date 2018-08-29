package uz.androidmk.fooddelivery.data.model;

/**
 * Created by Azamat on 8/9/2018.
 */

public class Banner {
    String thumbnail;
    int id;

    public Banner(){

    }
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
