package uz.androidmk.fooddelivery.ui.menu;

import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 8/14/2018.
 */

public interface MenuMvpPresenter<V extends MenuMvpView> extends MvpPresenter<V>{

    void requestMenuList();

    void requestBannerList();

    void setInstanceFirebase();
}
