package uz.androidmk.fooddelivery.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.Unbinder;
import uz.androidmk.fooddelivery.Utils.CommonUtils;

/**
 * Created by Azamat on 8/15/2018.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private Unbinder mUnbinder;

    private ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    protected abstract void setUp(View view);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) context;
            mActivity = activity;
          //  activity.onfra
        }
    }

    public BaseActivity getBaseActivity(){
        return mActivity;
    }

    public void setUnbinder(Unbinder unbinder){
        this.mUnbinder = unbinder;
    }

    @Override
    public void onDestroy() {
        if(mUnbinder != null)
            mUnbinder.unbind();

        super.onDestroy();
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showProgressDialog(this.getContext());
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
}
