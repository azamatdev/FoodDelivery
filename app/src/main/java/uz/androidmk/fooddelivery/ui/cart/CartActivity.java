package uz.androidmk.fooddelivery.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.ui.authUser.AuthActivity;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;
import uz.androidmk.fooddelivery.ui.cart.adapter.Callback;
import uz.androidmk.fooddelivery.ui.cart.adapter.CartAdapter;
import uz.androidmk.fooddelivery.ui.checkout.CheckoutActivity;

public class CartActivity extends BaseActivity implements CartMvpView, Callback{

    @BindView(R.id.checkout_recycler_view)
    RecyclerView recyclerView;

    @Inject
    CartMvpPresenter<CartMvpView> presenter;

    @Inject
    CartAdapter cartAdapter;

    @BindView(R.id.checkout_subtotal)
    TextView subtotal;

    @BindView(R.id.checkout_delivery_amount)
    TextView deliveryAmount;

    @BindView(R.id.checkout_total_cost)
    TextView totalCost;

    @BindView(R.id.cart_back)
    ImageView checkout_back;

    @BindView(R.id.cart_btn_order)
    Button cartButton;

    ArrayList<Food> list;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));

//        FirebaseAuth.getInstance().signOut();

        sharedPreferences = this.getSharedPreferences("Checks", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter.setCallback(this);
        recyclerView.setAdapter(cartAdapter);
        presenter.onAttach(this);
        presenter.getAllSelectedFoods();
    }

    @Override
    protected void setUpUi() {

    }

    private int total;
    @Override
    public void onFoodListReady(ArrayList<Food> list) {
//        this.list = list;
        Log.d("TagAdapt", "onFoodListReady: ");
        this.list = list;
        updateUi(list);
        cartAdapter.addItems(list);
    }

    @Override
    public void itemRemoved(String key) {
        presenter.itemRemoved(key);
        editor.remove(key);
        editor.apply();
        Log.d("TagDel", "itemRemoved: ");
    }

    public void updateUi(ArrayList<Food> list){
        total = 0;
        if(!list.isEmpty()){
            for(Food food : list){
                int price = Integer.parseInt(food.getPrice());
                total += price;
            }
        }
        DecimalFormat newDf = new DecimalFormat("#,###");
        String newFormat;
        if (total < 1000) {
            newDf = new DecimalFormat("###");
        }
        if (total > 1000 && total < 10000) {
            newDf = new DecimalFormat("#,###");
        }
        if (total >= 10000) {
            newDf = new DecimalFormat("##,###");
        }
        newFormat = newDf.format(total);
        subtotal.setText(newFormat);
        totalCost.setText(newFormat);
        deliveryAmount.setText("0");
    }


    public void onBackBtn(View view){
        finish();
    }

    public void onClickCartOrder(View view){
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent checkoutActivity = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(checkoutActivity);
            overridePendingTransition(R.anim.activity_slide_up,R.anim.activity_no_change);
        }
        else {
            Intent checkoutActivity = new Intent(CartActivity.this, AuthActivity.class);
            startActivity(checkoutActivity);
            overridePendingTransition(R.anim.activity_slide_up,R.anim.activity_no_change);
        }

    }
}
