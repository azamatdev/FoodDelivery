package uz.androidmk.fooddelivery.ui.food;

import java.util.ArrayList;

import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 8/10/2018.
 */

public interface FoodMvpPresenter<V extends FoodMvpView> extends MvpPresenter<V> {

    void requestSpecificFoodList(String menuId);

    void requestMenuList();

    void setInstanceFirebase();

    void addSelectedFood(Food food);

    void removeSelectedFood(String key);
}
