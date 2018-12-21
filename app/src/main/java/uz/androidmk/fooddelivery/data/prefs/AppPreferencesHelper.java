package uz.androidmk.fooddelivery.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import uz.androidmk.fooddelivery.di.ApplicationContext;

/**
 * Created by Azamat on 9/8/2018.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper{

    private static final String prefFileName = "Checks";

    public static final  String LANGUAGE_ENGLISH   = "en";
    public static final  String LANGUAGE_UZBEK = "uz";
    public static final  String LANGUAGE_RUSSIAN = "ru";

    private static final String LANGUAGE_KEY  = "language_key";


    private SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public SharedPreferences getPrefsReference() {
        return mPrefs;
    }


    public String getLanguage(){
        return mPrefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH);
    }

    public void setLanguage(String language){
        mPrefs.edit().putString(LANGUAGE_KEY, language).commit();
    }

}
