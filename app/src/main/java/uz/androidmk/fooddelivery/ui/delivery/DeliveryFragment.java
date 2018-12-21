package uz.androidmk.fooddelivery.ui.delivery;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.Utils.RobotoBold;
import uz.androidmk.fooddelivery.data.db.model.DeliveryState;
import uz.androidmk.fooddelivery.di.component.ActivityComponent;
import uz.androidmk.fooddelivery.ui.base.BaseFragment;
import uz.androidmk.fooddelivery.ui.favourite.FavouriteFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends BaseFragment implements DeliveryMvpView {

    @BindView(R.id.txt_order_placed)
    RobotoBold txt_order_placed;

    @BindView(R.id.txt_preparing)
    RobotoBold txt_preparing;

    @BindView(R.id.txt_dispatching)
    RobotoBold txt_dispatching;

    @BindView(R.id.txt_delivered)
    RobotoBold txt_delivered;

    @BindView(R.id.delivery_img_placed)
    ImageView img_placed;

    @BindView(R.id.delivery_img_preparing)
    ImageView img_preparing;

    @BindView(R.id.delivery_img_dispatching)
    ImageView img_dispatching;

    @BindView(R.id.delivery_img_delivered)
    ImageView img_delivered;

    @BindView(R.id.animation_placed)
    LottieAnimationView anim_placed;

    @BindView(R.id.animation_preparing)
    LottieAnimationView anim_preparing;

    @BindView(R.id.animation_dispatched)
    LottieAnimationView anim_dispatched;

    @BindView(R.id.animation_delivered)
    LottieAnimationView anim_delivered;
    @Inject
    DeliveryMvpPresenter<DeliveryMvpView> presenter;

    public DeliveryFragment() {
        // Required empty public constructor
    }


    public static DeliveryFragment newInstance() {
        DeliveryFragment fragment = new DeliveryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_temp, container, false);
        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
            setUnbinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onCheckState();
    }

    @Override
    public void onStateRecieved(DeliveryState deliveryState) {
        Log.d("Taqqii", "onStateRecieved: ");

        //PLACED
        if (deliveryState.isPlaced()){
            img_placed.setImageResource(R.drawable.d_double_tick_white);
            img_placed.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular_red));
            anim_placed.setVisibility(View.VISIBLE);
            anim_placed.playAnimation();
        }
        else{
            if(anim_placed.isAnimating()){
                anim_placed.cancelAnimation();
                anim_placed.setVisibility(View.GONE);
            }
            img_placed.setImageResource(R.drawable.d_double_tick);
            img_placed.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular));
        }

        //PREPARING
        if (deliveryState.isPreparing()){
            if(anim_placed.isAnimating()){
                anim_placed.cancelAnimation();
                anim_placed.setVisibility(View.GONE);
            }
            img_preparing.setImageResource(R.drawable.d_cooking_awesome_white);
            img_preparing.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular_red));
            anim_preparing.setVisibility(View.VISIBLE);
            anim_preparing.playAnimation();
        }
        else{
            if(anim_preparing.isAnimating()){
                anim_preparing.cancelAnimation();
                anim_preparing.setVisibility(View.GONE);
            }
            img_preparing.setImageResource(R.drawable.d_cooking_awesome);
            img_preparing.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular));
        }

        //DISPATCHING
        if (deliveryState.isDispatching()){
            if(anim_preparing.isAnimating()){
                anim_preparing.cancelAnimation();
                anim_preparing.setVisibility(View.GONE);
            }
            img_dispatching.setImageResource(R.drawable.d_delivery_white);
            img_dispatching.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular_red));
            anim_dispatched.setVisibility(View.VISIBLE);
            anim_dispatched.playAnimation();
        }
        else{
            if(anim_dispatched.isAnimating()){
                anim_dispatched.cancelAnimation();
                anim_dispatched.setVisibility(View.GONE);
            }
            img_dispatching.setImageResource(R.drawable.d_delivery);
            img_dispatching.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular));
        }

        //DELIVERED
        if (deliveryState.isDelivered()){
            if(anim_dispatched.isAnimating()){
                anim_dispatched.cancelAnimation();
                anim_dispatched.setVisibility(View.GONE);
            }
            img_delivered.setImageResource(R.drawable.d_homepage_white);
            img_delivered.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular_red));
            anim_delivered.setVisibility(View.VISIBLE);
            anim_delivered.playAnimation();

        }
        else{
            if(anim_delivered.isAnimating()){
                anim_delivered.cancelAnimation();
                anim_delivered.setVisibility(View.GONE);
            }
            img_delivered.setImageResource(R.drawable.d_homepage);
            img_delivered.setBackground(ContextCompat.getDrawable(getBaseActivity(),R.drawable.delivery_circular));
        }

    }
}
