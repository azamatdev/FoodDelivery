package uz.androidmk.fooddelivery.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.di.ApplicationContext;

import static android.content.ContentValues.TAG;

/**
 * Created by Azamat on 9/3/2018.
 */

@Singleton
public class AppDbHelper extends SQLiteOpenHelper implements DbHelper {

    private static String DB_NAME = "loook";
    private String TABLE_SELECTED_FOOD = "selectedFood";
    private static int version = 1;
    private static String KEY_FOOD_ID = "id";
    private static String KEY_FOOD_TITLE = "title";
    private static String KEY_FOOD_THUMBNAIL = "thumbnail";
    private static String KEY_FOOD_CATEGORY_ID = "categoryId";
    private static String KEY_FOOD_PRICE = "price";
    private static String KEY_FOOD_KEY = "key";

    //    private static String DB_NAME = "loook";
//    private static String DB_NAME = "loook";
    @Inject
    public AppDbHelper(@ApplicationContext Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SELECTED_TABLE = "CREATE TABLE " + TABLE_SELECTED_FOOD +
                "("
                + KEY_FOOD_ID + " INTEGER PRIMARY KEY,"
                + KEY_FOOD_CATEGORY_ID + " TEXT,"
                + KEY_FOOD_TITLE + " TEXT,"
                + KEY_FOOD_THUMBNAIL + " TEXT,"
                + KEY_FOOD_PRICE + " TEXT,"
                + KEY_FOOD_KEY + " TEXT)";

         db.execSQL(CREATE_SELECTED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELECTED_FOOD);
        }
    }

    public void addFoodToSelectedList(Food food){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_FOOD_CATEGORY_ID, food.getCategoryId());
            contentValues.put(KEY_FOOD_PRICE, food.getPrice());
            contentValues.put(KEY_FOOD_THUMBNAIL, food.getThumbnail());
            contentValues.put(KEY_FOOD_TITLE, food.getTitle());
            contentValues.put(KEY_FOOD_KEY, food.getKey());

            db.insert(TABLE_SELECTED_FOOD,null, contentValues);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.d(TAG, "addFoodToSelectedList: " + e.getMessage());
        }
        finally {
            db.endTransaction();
        }

    }

    public void removeFoodSelectedList(String key){
        Log.d("TagDel", "removeFoodSelectedList: " + key);
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_SELECTED_FOOD, KEY_FOOD_KEY  + "=" + key, null);
    }

    public Observable<ArrayList<Food>> getSelectedFoodList(){
        return Observable.fromCallable(new Callable<ArrayList<Food>>() {
            @Override
            public ArrayList<Food> call() throws Exception {
                ArrayList<Food> selectedFoodList = new ArrayList<>();

                String foodSelectQuery = "SELECT * FROM " + TABLE_SELECTED_FOOD;
                SQLiteDatabase db = getReadableDatabase();
                db.beginTransaction();

                Cursor cursor = db.rawQuery(foodSelectQuery, null);

                try{
                    if (cursor.moveToFirst())
                        do{
                            Food food = new Food();
                            food.setTitle(cursor.getString(cursor.getColumnIndex(KEY_FOOD_TITLE)));
                            food.setPrice(cursor.getString(cursor.getColumnIndex(KEY_FOOD_PRICE)));
                            food.setThumbnail(cursor.getString(cursor.getColumnIndex(KEY_FOOD_THUMBNAIL)));
                            food.setKey(cursor.getString(cursor.getColumnIndex(KEY_FOOD_KEY)));
                            selectedFoodList.add(food);
                        }while (cursor.moveToNext());

                    db.setTransactionSuccessful();
                }catch (Exception e){
                    Log.d(TAG, "addFoodToSelectedList: " + e.getMessage());
                }finally {
                    db.endTransaction();
                }

                return selectedFoodList;
            }
        });
    }

    public ArrayList<Food> getSelectedFoods(){
        ArrayList<Food> selectedFoodList = new ArrayList<>();

        String foodSelectQuery = "SELECT * FROM " + TABLE_SELECTED_FOOD;
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();

        Cursor cursor = db.rawQuery(foodSelectQuery, null);

        try{
            if (cursor.moveToFirst())
                do{
                    Food food = new Food();
                    food.setTitle(cursor.getString(cursor.getColumnIndex(KEY_FOOD_TITLE)));
                    food.setPrice(cursor.getString(cursor.getColumnIndex(KEY_FOOD_PRICE)));
                    food.setThumbnail(cursor.getString(cursor.getColumnIndex(KEY_FOOD_THUMBNAIL)));
                    food.setKey(cursor.getString(cursor.getColumnIndex(KEY_FOOD_KEY)));
                    food.setSpicy(false); /////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    selectedFoodList.add(food);
                }while (cursor.moveToNext());

            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.d(TAG, "addFoodToSelectedList: " + e.getMessage());
        }finally {
            db.endTransaction();
        }

        return selectedFoodList;
    }

}
