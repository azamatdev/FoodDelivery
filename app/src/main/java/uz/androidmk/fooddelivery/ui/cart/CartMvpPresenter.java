package uz.androidmk.fooddelivery.ui.cart;

import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 8/28/2018.
 */

public interface CartMvpPresenter<V extends CartMvpView> extends MvpPresenter<V> {

    void getAllSelectedFoods();

    void itemRemoved(String key);

}
