package uz.androidmk.fooddelivery.Utils;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import uz.androidmk.fooddelivery.R;

/**
 * Created by Azamat on 8/9/2018.
 */

public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();

        if (position < -1) { // [-Infinity,-1)
            page.setAlpha(1f);

        } else if (position <= 0) { // [-1,0]
            ImageView imageView = page.findViewById(R.id.imageViewBanner);

                    imageView.setTranslationX(-position * pageWidth/2f);

        } else if (position <= 1) { // (0,1]
            ImageView imageView = page.findViewById(R.id.imageViewBanner);



        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(1f);
        }
    }
}
