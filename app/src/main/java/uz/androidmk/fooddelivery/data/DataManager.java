package uz.androidmk.fooddelivery.data;

import java.util.ArrayList;

import io.reactivex.Observable;
import uz.androidmk.fooddelivery.data.db.AppDbHelper;
import uz.androidmk.fooddelivery.data.db.DbHelper;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.data.prefs.PreferencesHelper;

/**
 * Created by Azamat on 8/29/2018.
 */

public interface DataManager extends DbHelper,PreferencesHelper {
}
