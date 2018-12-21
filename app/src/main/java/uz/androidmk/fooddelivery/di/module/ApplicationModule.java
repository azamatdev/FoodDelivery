package uz.androidmk.fooddelivery.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.AppDataManager;
import uz.androidmk.fooddelivery.data.DataManager;
import uz.androidmk.fooddelivery.data.db.AppDbHelper;
import uz.androidmk.fooddelivery.data.db.DbHelper;
import uz.androidmk.fooddelivery.data.prefs.AppPreferencesHelper;
import uz.androidmk.fooddelivery.data.prefs.PreferencesHelper;
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
    DataManager provideDataManager(AppDataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper dbHelper){
        return dbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePrefHelper(AppPreferencesHelper preferencesHelper){
        return preferencesHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig(){
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("blogger_sans_medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
