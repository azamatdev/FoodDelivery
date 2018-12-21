package uz.androidmk.fooddelivery.ui.cart;

import java.util.ArrayList;

import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.ui.base.MvpView;

/**
 * Created by Azamat on 8/28/2018.
 */

public interface CartMvpView extends MvpView{

    void onFoodListReady(ArrayList<Food> list);
}
