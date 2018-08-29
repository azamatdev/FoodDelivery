package uz.androidmk.fooddelivery.ui.main;

import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;
import uz.androidmk.fooddelivery.ui.delivery.DeliveryFragment;
import uz.androidmk.fooddelivery.ui.favourite.FavouriteFragment;
import uz.androidmk.fooddelivery.ui.menu.MenuFragment;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    FragmentTransaction fragmentTransaction;

    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));
//        presenter = new MainPresenter<>();
        presenter.onAttach(this); // this means we now attach the presenter with main activity

        setUpUi();

        if (savedInstanceState == null)
            bottomNavigationView.setSelectedItemId(R.id.action_menu);

    }

    @Override
    protected void setUpUi() {

        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }


        selectedFragment = null;


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Class fragment = null;
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_favourite:
                        fragment = FavouriteFragment.newInstance();
//                        fragment = FavouriteFragment.class;
                        break;
                    case R.id.action_menu:
                        fragment = MenuFragment.newInstance();
//                        fragment = MenuFragment.class;
                        break;
                    case R.id.action_delivery:
                        fragment = DeliveryFragment.newInstance();
//                        fragment = DeliveryFragment.class;
                        break;
                }
//
//                try {
//                    selectedFragment = (Fragment) fragment.newInstance();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.place_holder, fragment).commit();
                return true;
            }
        });


    }


}
