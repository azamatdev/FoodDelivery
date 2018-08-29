package uz.androidmk.fooddelivery.ui.main;

import javax.inject.Inject;

import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 8/8/2018.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(){

    }
}
