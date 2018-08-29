package uz.androidmk.fooddelivery.data.model;

/**
 * Created by Azamat on 8/16/2018.
 */

public class Menu {

    String menuTitle;
    String menuThumbnail;
    String menuId;

    public Menu(){

    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuThumbnail() {
        return menuThumbnail;
    }

    public void setMenuThumbnail(String menuThumbnail) {
        this.menuThumbnail = menuThumbnail;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}
