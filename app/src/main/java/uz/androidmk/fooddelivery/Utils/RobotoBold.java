package uz.androidmk.fooddelivery.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Azamat on 8/22/2018.
 */


@SuppressLint("AppCompatCustomView")
public class RobotoBold extends TextView {

    public RobotoBold(Context context) {
        super(context);
        init();
    }

    public RobotoBold(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoBold(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        if(!isInEditMode()){
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"Roboto-Bold.ttf");
            setTypeface(typeface);
        }
    }
}
