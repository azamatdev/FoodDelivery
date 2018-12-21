package uz.androidmk.fooddelivery.data.db;

import java.util.ArrayList;

import io.reactivex.Observable;
import uz.androidmk.fooddelivery.data.db.model.Food;

/**
 * Created by Azamat on 9/3/2018.
 */

public interface DbHelper {

    void addFoodToSelectedList(Food food);

    void removeFoodSelectedList(String key);

    Observable<ArrayList<Food>> getSelectedFoodList();

    ArrayList<Food> getSelectedFoods();
}
