package uz.androidmk.fooddelivery.ui.checkout;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import uz.androidmk.fooddelivery.data.DataManager;
import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 9/12/2018.
 */

public class CheckoutPresenter<V extends CheckoutMvpView> extends BasePresenter<V>
                                implements CheckoutMvpPresenter<V>{

    @Inject
    public CheckoutPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onRequestFoodList() {
        getMvpView().onRecieveSelectedFoods(getDataManager().getSelectedFoods());
    }
}
