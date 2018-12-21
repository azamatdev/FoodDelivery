package uz.androidmk.fooddelivery.ui.delivery;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import uz.androidmk.fooddelivery.data.DataManager;
import uz.androidmk.fooddelivery.data.db.model.DeliveryState;
import uz.androidmk.fooddelivery.data.db.model.Menu;
import uz.androidmk.fooddelivery.data.db.model.Order;
import uz.androidmk.fooddelivery.ui.base.BasePresenter;

/**
 * Created by Azamat on 9/17/2018.
 */

public class DeliveryPresenter<V extends DeliveryMvpView> extends BasePresenter<V>
        implements DeliveryMvpPresenter<V> {

    DatabaseReference mDatabase;

    @Inject
    public DeliveryPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onCheckState() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

//        mDatabase.child("Orders").orderByChild("userId").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                DeliveryState deliveryState;
//                if(dataSnapshot.getValue() !=null){
//                    for (DataSnapshot data: dataSnapshot.getChildren()) {
//                        deliveryState = data.child("deliveryState").getValue(DeliveryState.class);
//                        getMvpView().onStateRecieved(deliveryState);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        mDatabase.child("DeliveryState").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DeliveryState deliveryState = dataSnapshot.getValue(DeliveryState.class);
                getMvpView().onStateRecieved(deliveryState);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
