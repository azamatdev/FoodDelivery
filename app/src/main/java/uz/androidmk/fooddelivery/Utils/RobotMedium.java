package uz.androidmk.fooddelivery.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Azamat on 10/28/2018.
 */

@SuppressLint("AppCompatCustomView")
public class RobotMedium extends TextView {
    public RobotMedium(Context context) {
        super(context);
        init();
    }

    private void init() {
        if(!isInEditMode()){
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"blogger-sans.medium.ttf");
            setTypeface(typeface);
        }
    }

    public RobotMedium(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotMedium(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
