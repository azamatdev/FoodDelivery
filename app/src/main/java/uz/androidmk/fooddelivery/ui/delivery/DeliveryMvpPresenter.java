package uz.androidmk.fooddelivery.ui.delivery;

import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 9/17/2018.
 */

public interface DeliveryMvpPresenter<V extends DeliveryMvpView> extends MvpPresenter<V>{

    void onCheckState();
}
