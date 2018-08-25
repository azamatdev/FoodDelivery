package uz.androidmk.fooddelivery.ui.food;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.model.Food;
import uz.androidmk.fooddelivery.model.Menu;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;
import uz.androidmk.fooddelivery.ui.food.Adapter.FoodAdapter;
import uz.androidmk.fooddelivery.ui.food.Adapter.MenuSelectedAdapter;
import uz.androidmk.fooddelivery.ui.menu.adapter.MenuAdapter;

public class FoodActivity extends BaseActivity implements FoodMvpView {

    @BindView(R.id.food_recyler_view)
    RecyclerView foodRecyclerView;

    @BindView(R.id.back_arrow)
    ImageView btn_back;

    @BindView(R.id.food_select_recyler_view)
    RecyclerView foodSelectRecycle;

    @BindView(R.id.btn_bottom_sheet_expanded)
    ImageView btn_expand_collapse;

    FoodAdapter foodAdapter;

    MenuSelectedAdapter menuAdapter;

    FoodMvpPresenter<FoodMvpView> presenter;

    ArrayList<Food> listOfFoods;

    ArrayList<Menu> menuList;
    String menuId;

    boolean isVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        presenter = new FoodPresenter<>();
        setUnbinder(ButterKnife.bind(this));

        presenter.onAttach(this);

        menuId = getIntent().getStringExtra("menuId");

        listOfFoods = new ArrayList<>();
        foodAdapter = new FoodAdapter(listOfFoods);

        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        foodRecyclerView.setHasFixedSize(true);
        foodRecyclerView.setAdapter(foodAdapter);

        menuList = new ArrayList<>();
        menuAdapter = new MenuSelectedAdapter(menuList);
        menuAdapter.setCallBack(this);
        foodSelectRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        foodSelectRecycle.setHasFixedSize(true);
        foodSelectRecycle.setAdapter(menuAdapter);

        presenter.requestSpecificFoodList(menuId);
        presenter.requestMenuList();

        // slide-up animation
        final Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        isVisible = true;
        btn_expand_collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible) {
                    foodSelectRecycle.setVisibility(View.GONE);
                    foodSelectRecycle.animate().alpha(1.0f);
                    btn_expand_collapse.setImageResource(R.drawable.ic_arrow_up);
                    isVisible = false;
                }
                else {
                    foodSelectRecycle.setVisibility(View.VISIBLE);
                    foodSelectRecycle.startAnimation(slideUp);
                    btn_expand_collapse.setImageResource(R.drawable.ic_arrow_down);
                    isVisible = true;
                }
             }
        });

    }

    @Override
    protected void setUpUi() {
    }


    public ArrayList<Food> getListOfFoods() {

//        String [] names = {"Classic",
//        "Loook", "Bigger", "Chicken longer", "Hamburger", "CheeseBurger", "Double cheese",
//        "Chili longer", "Snack wrap"};
//
//        String [] prices = {"8600", "9900", "11900", "9900", "11100", "12900", "20700", "10800", "7900"};
//
//        for (int i = 0; i < names.length ; i++) {
//            Food food = new Food();
//            food.setFood_name(names[i]);
//            food.setPrice(prices[i]);
//            food.setImage(R.drawable.category_food);
//            listOfFoods.add(food);
//        }

        return listOfFoods;
    }

    public void backPressed(View view) {
        finish();
    }

    @Override
    public void onFoodListReady(ArrayList<Food> foods) {
        foodAdapter.addItems(foods);
    }

    @Override
    public void onMenuListReady(ArrayList<Menu> list) {
        menuAdapter.addItems(list);
    }
}
