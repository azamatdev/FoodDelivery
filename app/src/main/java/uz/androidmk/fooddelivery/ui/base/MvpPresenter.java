package uz.androidmk.fooddelivery.ui.base;

/**
 * Created by Azamat on 8/8/2018.
 */

// Every view should be attached to presenter,
    // so we need generic class to satisfy all types of presenters
    // with their related view type <V extends MvpView>

public interface MvpPresenter<V extends MvpView>{

    void onAttach(V mvpView);

    void onDetach();
}
