package uz.androidmk.fooddelivery.ui.delivery;

import uz.androidmk.fooddelivery.data.db.model.DeliveryState;
import uz.androidmk.fooddelivery.ui.base.MvpView;

/**
 * Created by Azamat on 9/17/2018.
 */

public interface DeliveryMvpView extends MvpView{

    void onStateRecieved(DeliveryState deliveryState);
}
