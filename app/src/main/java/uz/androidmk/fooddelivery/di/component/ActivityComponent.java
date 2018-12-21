package uz.androidmk.fooddelivery.di.component;

import dagger.Component;
import uz.androidmk.fooddelivery.di.PerActivity;
import uz.androidmk.fooddelivery.di.module.ActivityModule;
import uz.androidmk.fooddelivery.ui.authUser.AuthActivity;
import uz.androidmk.fooddelivery.ui.cart.CartActivity;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutActivity;
import uz.androidmk.fooddelivery.ui.delivery.DeliveryFragment;
import uz.androidmk.fooddelivery.ui.favourite.FavouriteFragment;
import uz.androidmk.fooddelivery.ui.food.FoodActivity;
import uz.androidmk.fooddelivery.ui.main.MainActivity;
import uz.androidmk.fooddelivery.ui.menu.MenuFragment;

/**
 * Created by Azamat on 8/29/2018.
 */

@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(FoodActivity foodActivity);

    void inject(CartActivity cartActivity);

    void inject(CheckoutActivity checkoutActivity);

    void inject(AuthActivity authActivity);

    void inject(MenuFragment menuFragment);

    void inject(DeliveryFragment deliveryFragment);

    void inject(FavouriteFragment favouriteFragment);

}
