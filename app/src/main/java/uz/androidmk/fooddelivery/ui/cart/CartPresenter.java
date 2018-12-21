package uz.androidmk.fooddelivery.ui.cart;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uz.androidmk.fooddelivery.data.DataManager;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 8/28/2018.
 */

public class CartPresenter<V extends CartMvpView> extends BasePresenter<V>
                                implements CartMvpPresenter<V> {

    @Inject
    public CartPresenter(DataManager dataManager, CompositeDisposable compositeDisposable){
        super(dataManager, compositeDisposable);
    }


    @Override
    public void getAllSelectedFoods() {
//        compositeDisposable = new CompositeDisposable();
        getCompositeDisposable().add(
                getDataManager().getSelectedFoodList()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Food>>() {
                    @Override
                    public void accept(ArrayList<Food> list) throws Exception {
                        Log.d("RxJava", "accept: " + list.size());
                        getMvpView().onFoodListReady(list);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("RxJava", "accept: " + throwable.getMessage());
                    }
                })
        );

//        getMvpView().onFoodListReady(getDataManager().getSelectedFoods());
    }

    @Override
    public void itemRemoved(String key) {
//        Log.d("TagDel", "itemRemoved: P" + getDataManager().removeFoodSelectedList(key));
        getDataManager().removeFoodSelectedList(key);
    }
}
