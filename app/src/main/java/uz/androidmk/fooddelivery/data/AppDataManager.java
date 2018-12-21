package uz.androidmk.fooddelivery.data;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import uz.androidmk.fooddelivery.data.db.DbHelper;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.data.prefs.PreferencesHelper;
import uz.androidmk.fooddelivery.di.ApplicationContext;

/**
 * Created by Azamat on 8/29/2018.
 */

@Singleton
public class AppDataManager implements DataManager {

    private ArrayList<Food> selectedFoodList;

    private DbHelper dbHelper;
    private PreferencesHelper prefs;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper){
        this.dbHelper = dbHelper;
        this.prefs = preferencesHelper;

    }


    //Database logic
    @Override
    public void addFoodToSelectedList(Food food) {
        dbHelper.addFoodToSelectedList(food);
    }

    @Override
    public void removeFoodSelectedList(String key) {
        dbHelper.removeFoodSelectedList(key);
    }

    @Override
    public Observable<ArrayList<Food>> getSelectedFoodList() {
        return dbHelper.getSelectedFoodList();
    }

    @Override
    public ArrayList<Food> getSelectedFoods() {
        return dbHelper.getSelectedFoods();
    }

    @Override
    public SharedPreferences getPrefsReference() {
        return prefs.getPrefsReference();
    }
}
