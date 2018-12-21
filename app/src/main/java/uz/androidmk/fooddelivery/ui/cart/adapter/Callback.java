package uz.androidmk.fooddelivery.ui.cart.adapter;

import java.util.ArrayList;

import uz.androidmk.fooddelivery.data.db.model.Food;

/**
 * Created by Azamat on 9/5/2018.
 */

public interface Callback {

    void itemRemoved(String key);

    void updateUi(ArrayList<Food> list);
}
