package uz.androidmk.fooddelivery.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uz.androidmk.fooddelivery.Utils.CommonUtils;

/**
 * Created by Azamat on 8/8/2018.
 */

// Base class needs to be abstract class
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private Unbinder mUnbinder;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
