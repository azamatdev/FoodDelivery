package uz.androidmk.fooddelivery.data.prefs;

import android.content.SharedPreferences;

import javax.inject.Singleton;

/**
 * Created by Azamat on 9/8/2018.
 */


public interface PreferencesHelper {

    SharedPreferences getPrefsReference();
}
