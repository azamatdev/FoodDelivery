package uz.androidmk.fooddelivery.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.data.db.model.Menu;
import uz.androidmk.fooddelivery.di.ActivityContext;
import uz.androidmk.fooddelivery.di.PerActivity;
import uz.androidmk.fooddelivery.ui.cart.CartMvpPresenter;
import uz.androidmk.fooddelivery.ui.cart.CartMvpView;
import uz.androidmk.fooddelivery.ui.cart.CartPresenter;
import uz.androidmk.fooddelivery.ui.cart.adapter.CartAdapter;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutMvpPresenter;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutMvpView;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutPresenter;
import uz.androidmk.fooddelivery.ui.delivery.DeliveryMvpPresenter;
import uz.androidmk.fooddelivery.ui.delivery.DeliveryMvpView;
import uz.androidmk.fooddelivery.ui.delivery.DeliveryPresenter;
import uz.androidmk.fooddelivery.ui.food.Adapter.CategoryAdapter;
import uz.androidmk.fooddelivery.ui.food.Adapter.FoodAdapter;
import uz.androidmk.fooddelivery.ui.food.FoodMvpPresenter;
import uz.androidmk.fooddelivery.ui.food.FoodMvpView;
import uz.androidmk.fooddelivery.ui.food.FoodPresenter;
import uz.androidmk.fooddelivery.ui.main.MainMvpPresenter;
import uz.androidmk.fooddelivery.ui.main.MainMvpView;
import uz.androidmk.fooddelivery.ui.main.MainPresenter;
import uz.androidmk.fooddelivery.ui.menu.MenuMvpPresenter;
import uz.androidmk.fooddelivery.ui.menu.MenuMvpView;
import uz.androidmk.fooddelivery.ui.menu.MenuPresenter;
import uz.androidmk.fooddelivery.ui.menu.adapter.HomeBannerAdapter;
import uz.androidmk.fooddelivery.ui.menu.adapter.MenuAdapter;

/**
 * Created by Azamat on 8/29/2018.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity){
        this.mActivity = activity;
    }

    // activity itself and context
    @Provides
    @ActivityContext
    Context provideContext(){
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity(){
        return mActivity;
    }


    //For RxJava extensions
    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }
    //presenters
    //1. Inject the presenter's constructor, so that the dagger will get it loaded
    // into its own dependency container
    //2.Then we can receive the instance of the object via parameter here
    //3.We should use PerActivity identifier, as there is one type of presenter for per activity
    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> mainPresenter){
        return mainPresenter;
    }

    //here the presenter are the type of Fragment, we don't use perActivity
    @Provides
    MenuMvpPresenter<MenuMvpView> provideMenuPresenter(MenuPresenter<MenuMvpView> menuPresenter){
        return menuPresenter;
    }

    @Provides
    @PerActivity
    FoodMvpPresenter<FoodMvpView> provideFoodPresenter(FoodPresenter<FoodMvpView> foodPresenter){
        return foodPresenter;
    }

    @Provides
    @PerActivity
    CartMvpPresenter<CartMvpView> provideCartPresenter(CartPresenter<CartMvpView> cartPresenter){
        return cartPresenter;
    }

    @Provides
    @PerActivity
    CheckoutMvpPresenter<CheckoutMvpView> provideCheckoutPresenter(CheckoutPresenter<CheckoutMvpView> checkoutPresenter){
        return checkoutPresenter;
    }

    @Provides
    @PerActivity
    DeliveryMvpPresenter<DeliveryMvpView> provideDeliveryPresenter(DeliveryPresenter<DeliveryMvpView> deliveryPresenter){
        return deliveryPresenter;
    }



    ///Adapters
    @Provides
    HomeBannerAdapter provideHomeAdapter(AppCompatActivity activity){
        return new HomeBannerAdapter(activity.getBaseContext());
    }

    @Provides
    MenuAdapter provideMenuAdapter(){
        return new MenuAdapter(new ArrayList<Menu>());
    }

    @Provides
    CategoryAdapter provideCategoryAdapter(){
        return new CategoryAdapter(new ArrayList<Menu>());
    }

    @Provides
    FoodAdapter provideFoodAdapter(){
        return new FoodAdapter(new ArrayList<Food>());
    }

    @Provides
    CartAdapter provideCheckoutAdapter(){
        return new CartAdapter(new ArrayList<Food>());
    }


}
