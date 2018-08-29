package uz.androidmk.fooddelivery;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uz.androidmk.fooddelivery.di.component.ApplicationComponent;
import uz.androidmk.fooddelivery.di.component.DaggerApplicationComponent;
import uz.androidmk.fooddelivery.di.module.ApplicationModule;

/**
 * Created by Azamat on 8/7/2018.
 */

public class MvpApp extends Application {

    @Inject
    CalligraphyConfig calligraphyConfig;

    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent
                                .builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Roboto-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public ApplicationComponent getComponent(){
        return mApplicationComponent;
    }
}
