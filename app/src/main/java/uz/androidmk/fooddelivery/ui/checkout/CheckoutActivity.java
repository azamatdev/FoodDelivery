package uz.androidmk.fooddelivery.ui.checkout;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.model.Food;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;
import uz.androidmk.fooddelivery.ui.checkout.adapter.CheckoutAdapter;

public class CheckoutActivity extends BaseActivity {

    @BindView(R.id.checkout_recycler_view)
    RecyclerView recyclerView;

    @Inject
    CheckoutMvpPresenter<CheckoutMvpView> presenter;

    @Inject
    CheckoutAdapter checkoutAdapter;

    ArrayList<Food> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));

//        list = new ArrayList<>();
//        presenter = new CheckoutPresenter<>();
//        checkoutAdapter = new CheckoutAdapter(list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(checkoutAdapter);
    }

    @Override
    protected void setUpUi() {

    }
}
