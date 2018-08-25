package uz.androidmk.fooddelivery.ui.delivery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.ui.base.BaseFragment;
import uz.androidmk.fooddelivery.ui.favourite.FavouriteFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends BaseFragment {


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
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }

    @Override
    protected void setUp(View view) {

    }


}
