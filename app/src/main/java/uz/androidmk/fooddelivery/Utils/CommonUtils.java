package uz.androidmk.fooddelivery.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import uz.androidmk.fooddelivery.R;

/**
 * Created by Azamat on 8/22/2018.
 */

public final class CommonUtils {

    private CommonUtils(){
        //this class is never instantiated
    }

    public static ProgressDialog showProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static Typeface getBloggerBold(AssetManager assetManager){
        Typeface custom_font = Typeface.createFromAsset(assetManager, "blogger-sans.bold.ttf");
        return custom_font;
    }

    public static Typeface getBloggerMedium(AssetManager assetManager){
        Typeface custom_font = Typeface.createFromAsset(assetManager, "blogger-sans.medium.ttf");
        return custom_font;
    }


    public static String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        return formattedDate;
    }
}
