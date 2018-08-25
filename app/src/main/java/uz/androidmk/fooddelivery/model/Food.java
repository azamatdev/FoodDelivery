package uz.androidmk.fooddelivery.model;

/**
 * Created by Azamat on 8/10/2018.
 */

public class Food {

    public Food() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    //    int categoryId;
//    int price;
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String title;
    String thumbnail;
    String categoryId;
    String price;


}
