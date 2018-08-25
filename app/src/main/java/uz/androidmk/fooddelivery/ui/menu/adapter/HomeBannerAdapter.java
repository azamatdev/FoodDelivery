package uz.androidmk.fooddelivery.ui.menu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.model.Banner;

/**
 * Created by Azamat on 8/7/2018.
 */

public class HomeBannerAdapter extends PagerAdapter{

    private Context mContext;
    private List<Banner> banners;
    private int viewType;

    public HomeBannerAdapter(Context mContext, List<Banner> banners, int viewType) {
        this.mContext = mContext;
        this.banners = banners;
        this.viewType = viewType;
    }

    @Override
    public int getCount() {
        return banners.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;

        if(viewType == 0){
            view = inflater.inflate(R.layout.item_banner_big, container, false);
        if(viewType == 1)
            view = inflater.inflate(R.layout.item_banner_small, container, false);

        }
        ImageView imageView = view.findViewById(R.id.imageViewBanner);

        Banner banner = banners.get(position);

        Glide.with(mContext).load(banner.getThumbnail()).into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
