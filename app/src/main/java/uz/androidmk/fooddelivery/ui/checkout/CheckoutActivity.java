package uz.androidmk.fooddelivery.ui.checkout;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.Utils.CommonUtils;
import uz.androidmk.fooddelivery.data.db.model.DeliveryState;
import uz.androidmk.fooddelivery.data.db.model.Food;
import uz.androidmk.fooddelivery.data.db.model.Order;
import uz.androidmk.fooddelivery.ui.base.BaseActivity;

public class CheckoutActivity extends BaseActivity implements CheckoutMvpView {

    @BindView(R.id.checkout_edtxt_name)
    EditText edtxt_name;

    @BindView(R.id.checkout_edtxt_phone)
    MaskedEditText edtxt_phone;

    @BindView(R.id.txt_choose_location)
    TextView choose_location;

    @BindView(R.id.checkout_current_location)
    EditText currentLocation;

    @BindView(R.id.checkout_location_linear)
    LinearLayout locationLinear;

    @BindView(R.id.checkout_btn_order)
    RelativeLayout btn_order;

    @Inject
    CheckoutMvpPresenter<CheckoutMvpView> presenter;

    private static final String TAG = "CheckoutActivity";

    FusedLocationProviderClient mFusedLocationProviderClient;
    GeoDataClient mGeoDataClient;
    PlaceDetectionClient mPlaceDetectionClient;

    DatabaseReference mDatabase;

    List<Food> foodList;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //first need to inject activity, otherwise this activity will not be
        // configured by Dagger
        getActivityComponent().inject(this);

        setUnbinder(ButterKnife.bind(this));

        mDatabase = FirebaseDatabase.getInstance().getReference("Orders");

        String phone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(4);
        edtxt_phone.setText(phone);

        presenter.onAttach(this);
        presenter.onRequestFoodList();

        mGeoDataClient = Places.getGeoDataClient(this, null);
        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        setUpUi();

        showCurrentPlace();

        if (isServiceOk()) {
            if (mLocationPermissionGranted) {
                choose_location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mapsActivity = new Intent(CheckoutActivity.this, MapsActivity.class);
                        startActivity(mapsActivity);
                    }
                });
            }
        }

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!foodList.isEmpty() && foodList != null) {

                    Order order = new Order();

                            order.setName(edtxt_name.getText().toString());
                            order.setTel_number(edtxt_phone.getText().toString());
                            order.setLocation(currentLocation.getText().toString());
                            order.setLongitude(0.0);
                            order.setLatitude(0.0);
                            order.setDate(CommonUtils.getCurrentTimeUsingCalendar());
                            order.setFoods(foodList);
                            order.setDeliveryState(new DeliveryState());
                            order.setUserId(FirebaseAuth.getInstance().getUid());

                    mDatabase.push().setValue(order);

                } else {
                    Toast.makeText(CheckoutActivity.this, "No food is selected", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    protected void setUpUi() {

    }

    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOk: checking the google services location");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(CheckoutActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiceOk: The GOogle play service is connected");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServiceOk: Resolvable error, we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(CheckoutActivity.this, available, 9001);
            dialog.show();
        } else {
            Log.d(TAG, "isServiceOk: You can't make requests");
        }
        return false;
    }

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionGranted = false;

    private void getLocationPermission() {
//     * Request location permission, so that we can get the location of the
//     * device. The result of the permission request is handled by a callback,
//     * onRequestPermissionsResult.

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            showCurrentPlace();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    showCurrentPlace();
                }
            }
        }
    }

    private static final int M_MAX_ENTRIES = 5;
    String[] mLikelyPlaceNames;
    String[] mLikelyPlaceAddresses;
    String[] mLikelyPlaceAttributions;
    LatLng[] mLikelyPlaceLatLngs;

    private void showCurrentPlace() {

        if (mLocationPermissionGranted) {
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            @SuppressWarnings("MissingPermission") final Task<PlaceLikelihoodBufferResponse> placeResult =
                    mPlaceDetectionClient.getCurrentPlace(null);
            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                                if(likelyPlaces.get(0).getPlace().getAddress()!= null){
                                    currentLocation.setText(likelyPlaces.get(0).getPlace().getAddress());
                                }
                            } else {
                                Log.e(TAG, "Exception: %s", task.getException());
                            }
                        }
                    });
        } else {
            // The user has not granted permission.
            Log.i(TAG, "The user did not grant location permission.");

            // Prompt the user for permission.
            getLocationPermission();
        }
    }

    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }
                currentLocation.setText(markerSnippet);

            }
        };

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Chosen place")
                .setItems(mLikelyPlaceNames, listener)
                .show();
    }

    @Override
    public void onRecieveSelectedFoods(List<Food> foods) {
        foodList = foods;
    }
}
