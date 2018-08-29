package uz.androidmk.fooddelivery.ui.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.model.Food;
import uz.androidmk.fooddelivery.data.model.Menu;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutActivity;
import uz.androidmk.fooddelivery.ui.food.Adapter.CategoryAdapter;
import uz.androidmk.fooddelivery.ui.food.Adapter.FoodAdapter;
import uz.androidmk.fooddelivery.ui.food.Adapter.FoodSelectListener;

public class FoodActivity extends BaseActivity implements FoodMvpView, FoodSelectListener {

    @BindView(R.id.food_recyler_view)
    RecyclerView foodRecyclerView;

    @BindView(R.id.back_arrow)
    ImageView btn_back;

    @BindView(R.id.food_select_recyler_view)
    RecyclerView foodSelectRecycle;

    @BindView(R.id.btn_bottom_sheet_expanded)
    ImageView btn_expand_collapse;

    @BindView(R.id.food_add_to_cart)
    Button addToCart;

    @Inject
    FoodAdapter foodAdapter;

    @Inject
    CategoryAdapter menuAdapter;

    @Inject
    FoodMvpPresenter<FoodMvpView> presenter;

    ArrayList<Food> listOfFoods;

    ArrayList<Menu> menuList;

    String menuId;

    HashMap<String, ArrayList<Food>> selectedFoods;

    int selectedPage;

    boolean isVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getActivityComponent().inject(this);
//        presenter = new FoodPresenter<>();
        selectedFoods = new HashMap<>();
        setUnbinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        presenter.setInstanceFirebase();

        menuId = getIntent().getStringExtra("menuId");

        selectedPage = Integer.parseInt(menuId);

        //food list
//        listOfFoods = new ArrayList<>();
//        foodAdapter = new FoodAdapter(listOfFoods);
        foodAdapter.setFoodSelectListener(this);

        foodRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        foodRecyclerView.setHasFixedSize(true);
        foodRecyclerView.setAdapter(foodAdapter);

        // bottom menu list
//        menuList = new ArrayList<>();
//        menuAdapter = new CategoryAdapter(menuList);
        menuAdapter.setFoodSelectListener(this);
        menuAdapter.setCallBack(this);

        foodSelectRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        foodSelectRecycle.setHasFixedSize(true);
        foodSelectRecycle.setAdapter(menuAdapter);

        presenter.requestSpecificFoodList(menuId);
        presenter.requestMenuList();

        // slide-up animation for bottom category selection
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

    public void backPressed(View view) {
        finish();
    }

    //from mvpView
    @Override
    public void onFoodListReady(ArrayList<Food> foods, String menuId) {
//        this.listOfFoods = foods;
        selectedFoods.put(menuId, foods);
        foodAdapter.addItems(selectedFoods.get(menuId));
    }

    @Override
    public void onMenuListReady(ArrayList<Menu> list) {
        this.menuList = list;
        menuAdapter.addItems(list);
    }

    @Override
    public void onFoodList() {
        foodAdapter.addItems(selectedFoods.get(menuId));
    }

    //From food select listener
    @Override
    public void onFoodSelected(int position) {
//        selectedFoods.put(listOfFoods.get(position).getCategoryId(), listOfFoods);
    }

    @Override
    public void onCategorySelected(int position) {
        if(selectedFoods.containsKey(Integer.toString(position))) {
            Log.d("ConditionC", "adapter");
            foodAdapter.addItems(selectedFoods.get(Integer.toString(position)));
        }else {
            Log.d("ConditionC", "presenter");
            presenter.requestSpecificFoodList(Integer.toString(position));
        }
    }

    public void onAddToCart(View view){
        Intent checkout = new Intent(FoodActivity.this, CheckoutActivity.class);
        startActivity(checkout);
    }




}
