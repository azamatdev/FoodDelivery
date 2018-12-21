package uz.androidmk.fooddelivery.ui.menu;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import uz.androidmk.fooddelivery.data.DataManager;
import uz.androidmk.fooddelivery.data.db.model.Banner;
import uz.androidmk.fooddelivery.data.db.model.Menu;
import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 8/14/2018.
 */

public class MenuPresenter<V extends MenuMvpView> extends BasePresenter<V>
                            implements  MenuMvpPresenter<V> {

    DatabaseReference mDatabase;
    ArrayList<Menu> networkMenuList;
    ArrayList<Banner> bannerList;

    DatabaseReference connectedRef;
    private StorageReference mStorageRef;

    @Inject
    public MenuPresenter(DataManager dataManager, CompositeDisposable compositeDisposable){
        super(dataManager, compositeDisposable);
    }

    public void setInstanceFirebase(){
        //        connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
    }

    @Override
    public void requestMenuList()
    {
        getMvpView().showLoading();
        checkViewAttached();

        networkMenuList = new ArrayList<>(); // Result will be holded Here

        mDatabase.child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
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

                getMvpView().hideLoading();
                if(isViewAttached())
                    getMvpView().onMenuListReady(networkMenuList);
                else
                    Log.d("Banners", "onDataChange: ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("onCancelled", "onCancelled: ");
            }
        });
//        getMvpView().onMenuListReady(getList());
    }


    @Override
    public void requestBannerList() {
        checkViewAttached();
        bannerList = new ArrayList<>();
        mDatabase.child("Banners").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Banners", "onDataChange: " + dataSnapshot.getChildrenCount());
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Log.d("Banners", "onDataChange: " + dsp.getValue());
                    Banner banner = new Banner();
                    banner.setThumbnail(dsp.getValue().toString());
                    bannerList.add(banner);
//                    Food food = dataSnapshot.getValue(Food.class);
                }
                if (getMvpView() != null)
                    getMvpView().onBannerListReady(bannerList);
                else
                    Log.d("Banners", "onDataChange: Vayooo" );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        getMvpView().onBannerListReady(getBannerList());
    }

//    public List<Banner> getBannerList(){
//        ArrayList<Banner> menuList = new ArrayList<>();
//
//        String[] thumbnails = {"Sandwiches",
//                "Combo",
//                "Chicken Meals",
//                "Deserts"
//        };
//
//        int[] images = {R.drawable.b1_aktsiya,
//                R.drawable.b2_box_master,
//                R.drawable.b3_apetizers,
//                R.drawable.b4_desert,
//        };
//        for (int i = 0; i < thumbnails.length; i++) {
//            Banner banner = new Banner();
//            banner.setId(images[i]);
//            banner.setThumbnail(thumbnails[i]);
//            menuList.add(banner);
//        }
//
//        return menuList;
//    }
//    public ArrayList<Category> getList() {
//        ArrayList<Category> menuList = new ArrayList<>();
//
//        /*String[] names = {"Sandwiches",
//                "Combo",
//                "Chicken Meals",
//                "Pizza",
//                "Kids Menu",
//                "Appetizers",
//                "Beverages",
//                "Deserts"};
//
//        int[] images = {R.drawable.sandwiches,
//                R.drawable.combo,
//                R.drawable.chicken_meals,
//                R.drawable.pizza,
//                R.drawable.kids_meal,
//                R.drawable.appetizers,
//                R.drawable.beverages,
//                R.drawable.dessert};
//
//        for (int i = 0; i < names.length; i++) {
//            Category category = new Category();
//            category.setTitle(names[i]);
//            category.setImage(images[i]);
//            menuList.add(category);
//        }
//*/
//        // Write a message to the database
////        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//
////        food.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//
//
//        return menuList;
//    }
}
