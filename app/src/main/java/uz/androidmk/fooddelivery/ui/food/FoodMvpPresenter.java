package uz.androidmk.fooddelivery.ui.food;

import java.util.ArrayList;
import java.util.HashMap;

import uz.androidmk.fooddelivery.model.Food;
import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 8/10/2018.
 */

public interface FoodMvpPresenter<V extends FoodMvpView> extends MvpPresenter<V> {

    void requestSpecificFoodList(String menuId);

    void requestMenuList();

}
