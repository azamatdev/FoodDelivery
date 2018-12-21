package uz.androidmk.fooddelivery.ui.menu;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.Utils.ParallaxPageTransformer;
import uz.androidmk.fooddelivery.data.db.model.Banner;
import uz.androidmk.fooddelivery.data.db.model.Menu;
import uz.androidmk.fooddelivery.di.component.ActivityComponent;
import uz.androidmk.fooddelivery.ui.base.BaseFragment;
import uz.androidmk.fooddelivery.ui.menu.adapter.HomeBannerAdapter;
import uz.androidmk.fooddelivery.ui.menu.adapter.MenuAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment implements MenuMvpView {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.viewPagerHomeBanner)
    ViewPager viewPager;

    @BindView(R.id.indicator)
    CirclePageIndicator circlePageIndicator;

    @Inject
    MenuAdapter adapter;

    ArrayList<Menu> menuList;

    @Inject
    HomeBannerAdapter bannerAdapter;

    @Inject
    MenuMvpPresenter<MenuMvpView> presenter;


    //For Banner auto flipping
    Timer timer;
    long DELAY_MS = 500;
    long PERIOD_MS = 4000;
    int currentPage = 0;

    boolean isTimerRunning;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment menuFragment = new MenuFragment();
        Bundle args = new Bundle();
        menuFragment.setArguments(args);
        return menuFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);

        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
            setUnbinder(ButterKnife.bind(this, view));
//            presenter = new MenuPresenter<>();
            presenter.onAttach(this);
            presenter.setInstanceFirebase(); // temporary method
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        circlePageIndicator = view.findViewById(R.id.indicator);

//        recyclerView = view.findViewById(R.id.main_recycler_view);
        viewPager = view.findViewById(R.id.viewPagerHomeBanner);
//        menuList = new ArrayList<>();
//        adapter = new MenuAdapter(menuList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);

        presenter.requestMenuList();
        presenter.requestBannerList();
    }

    @Override
    protected void setUp(View view) {

    }

    private Handler handler;
    private Runnable update;

    @Override
    public void onBannerListReady(final List<Banner> banners) {
//        bannerAdapter = new HomeBannerAdapter(getActivity());
        bannerAdapter.setListAndType(banners, 0);

        viewPager.setPageTransformer(false, new ParallaxPageTransformer());
        viewPager.setAdapter(bannerAdapter);

        handler = new Handler();
        update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == banners.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
                isTimerRunning = true;
            }
        }, DELAY_MS, PERIOD_MS);


        circlePageIndicator.setViewPager(viewPager);
    }


    @Override
    public void onMenuListReady(ArrayList<Menu> list) {
        adapter.addItems(list);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        stopUpdates();
        super.onDestroyView();
    }

    private void stopUpdates() {
        if (handler != null)
            handler = null;
        if (update != null)
            update = null;
        if (isTimerRunning) {
            timer.cancel();
//            timer.purge();
            timer = null;
            isTimerRunning = false;
        }

    }


}
