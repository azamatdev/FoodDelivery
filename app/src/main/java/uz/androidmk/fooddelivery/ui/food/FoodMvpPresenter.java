package uz.androidmk.fooddelivery.ui.food;

import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 8/10/2018.
 */

public interface FoodMvpPresenter<V extends FoodMvpView> extends MvpPresenter<V> {

    void requestSpecificFoodList(String menuId);

    void requestMenuList();

    void setInstanceFirebase();
}
