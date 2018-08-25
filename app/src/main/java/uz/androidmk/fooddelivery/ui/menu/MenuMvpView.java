package uz.androidmk.fooddelivery.ui.menu;

import java.util.ArrayList;
import java.util.List;

import uz.androidmk.fooddelivery.model.Banner;
import uz.androidmk.fooddelivery.model.Category;
import uz.androidmk.fooddelivery.model.Food;
import uz.androidmk.fooddelivery.model.Menu;
import uz.androidmk.fooddelivery.ui.base.MvpView;

/**
 * Created by Azamat on 8/14/2018.
 */

public interface MenuMvpView extends MvpView{

    void onMenuListReady(ArrayList<Menu> list);

    void onBannerListReady(List<Banner> banners);
}
