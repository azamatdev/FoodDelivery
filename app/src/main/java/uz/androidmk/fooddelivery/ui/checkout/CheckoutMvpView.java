package uz.androidmk.fooddelivery.ui.checkout;

import java.util.List;

import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.ui.base.MvpView;

/**
 * Created by Azamat on 9/12/2018.
 */

public interface CheckoutMvpView extends MvpView {

    void onRecieveSelectedFoods(List<Food> foods);
}
