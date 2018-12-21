package uz.androidmk.fooddelivery.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import dagger.internal.DaggerCollections;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uz.androidmk.fooddelivery.MvpApp;
import uz.androidmk.fooddelivery.Utils.CommonUtils;
import uz.androidmk.fooddelivery.Utils.LocaleManager;
import uz.androidmk.fooddelivery.di.component.ActivityComponent;
import uz.androidmk.fooddelivery.di.component.DaggerActivityComponent;
import uz.androidmk.fooddelivery.di.module.ActivityModule;

/**
 * Created by Azamat on 8/8/2018.
 */

// Base class needs to be abstract class
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private Unbinder mUnbinder;

    private ProgressDialog progressDialog;

    private ActivityComponent mActivity;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setNewLocale("ru");
        mActivity = DaggerActivityComponent
                        .builder()
                        .activityModule(new ActivityModule(this))
                        .applicationComponent(((MvpApp)getApplication()).getComponent())
                        .build();


    }



    private boolean setNewLocale(String language) {
        LocaleManager.setNewLocale(this, language);

        return true;
    }

    public ActivityComponent getActivityComponent() {
        return mActivity;
    }

    public void setUnbinder(Unbinder unbinder){
        mUnbinder =  unbinder;
    }

    @Override
    protected void onDestroy() {
        if(mUnbinder !=null)
            mUnbinder.unbind();
        super.onDestroy();
    }

    protected abstract void setUpUi(); // this method is universal for all activity classes


    //for calligraphy font class
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showProgressDialog(this);
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

//    @Override
//    public void onError(String message) {
//
//    }
//
//    @Override
//    public void showMessage(String message) {
//
//    }

//    @Override
//    public boolean isNetworkConnected() {
//        return false;
//    }
//
//    @Override
//    public void hideKeyboard() {
//
//    }


}
