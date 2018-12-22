package uz.androidmk.fooddelivery.ui.food.Adapter;

import uz.androidmk.fooddelivery.data.db.model.Food;

/**
 * Created by Azamat on 8/25/2018.
 */

public interface FoodSelectListener {

    void onFoodSelected(Food food, int type);

    void onCategorySelected(int position);

}
