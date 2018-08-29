package uz.androidmk.fooddelivery.ui.food;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

import uz.androidmk.fooddelivery.data.model.Food;
import uz.androidmk.fooddelivery.data.model.Menu;
import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 8/10/2018.
 */

public class FoodPresenter<V extends FoodMvpView> extends BasePresenter<V>
                            implements FoodMvpPresenter<V>{

    DatabaseReference mDatabase;
    ArrayList<Menu> networkMenuList;

    @Inject
    public FoodPresenter() {

    }

    public void setInstanceFirebase(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
    }

    @Override
    public void requestSpecificFoodList(final String menuId) {
        mDatabase.child("Food")
                .orderByChild("categoryId")
                .equalTo(menuId)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("FoodTag", "onDataChange: " + dataSnapshot.getChildrenCount());
                ArrayList<Food> foodList = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Log.d("FoodTag", "onDataChange: "  + ds.getKey() + "\n" + ds.getValue() + "\n" +  ds.getValue().toString());
                    Food food = new Food();
//                    food.setCategoryId(ds.child("categoryId").getValue().toString());
                    food.setKey(ds.getKey());
                    food.setPrice(ds.child("price").getValue().toString());
                    food.setTitle(ds.child("title").getValue().toString());
                    food.setThumbnail(ds.child("thumbnail").getValue().toString());
                    foodList.add(food);
//                    allFoodList.put(ds.child("categoryId").getValue().toString(),foodList);
                }
                getMvpView().onFoodListReady(foodList, menuId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FoodTag", "onCancelled: " + databaseError.getDetails());
            }
        });
    }



    @Override
    public void requestMenuList() {
        checkViewAttached();

        networkMenuList = new ArrayList<>(); // Result will be holded Here

        mDatabase.child("Menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Menu menu = new Menu();
                    menu.setMenuId(dsp.child("menuId").getValue().toString());
                    menu.setMenuThumbnail(dsp.child("menuThumbnail").getValue().toString());
                    menu.setMenuTitle(dsp.child("menuTitle").getValue().toString());
                    networkMenuList.add(menu);
//                    Food food = dataSnapshot.getValue(Food.class);
                }

                if(isViewAttached())
                    getMvpView().onMenuListReady(networkMenuList);
                else
                    Log.d("Banners", "onDataChange: ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
