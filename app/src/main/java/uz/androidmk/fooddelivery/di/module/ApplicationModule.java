package uz.androidmk.fooddelivery.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.di.ApplicationContext;

/**
 * Created by Azamat on 8/29/2018.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig(){
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("Roboto-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
