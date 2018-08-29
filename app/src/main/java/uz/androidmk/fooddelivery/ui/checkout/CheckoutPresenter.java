package uz.androidmk.fooddelivery.ui.checkout;

import javax.inject.Inject;

import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 8/28/2018.
 */

public class CheckoutPresenter<V extends CheckoutMvpView> extends BasePresenter<V>
                                implements CheckoutMvpPresenter<V>{

    @Inject
    public CheckoutPresenter(){

    }
}
