package uz.androidmk.fooddelivery.ui.food.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.db.model.Food;

/**
 * Created by Azamat on 8/10/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private String TAG = "FoodAdapter";
    private ArrayList<Food> mFoodList;
    private FoodSelectListener foodSelectListener;

    // to keep the current state of the selected item
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    private SharedPreferences sharedPreferences;

    public void setFoodSelectListener(FoodSelectListener callback) {
        this.foodSelectListener = callback;
    }

    public void setSharedPreference(SharedPreferences sharedPreference) {
        this.sharedPreferences = sharedPreference;
    }


    public FoodAdapter(ArrayList<Food> mList) {
        this.mFoodList = mList;
    }

    public void addItems(ArrayList<Food> foods) {
        mFoodList = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Context mContext = holder.itemView.getContext();
        final Food food = mFoodList.get(position);
        final String key = food.getKey();


        //food price logic
        int price = Integer.parseInt(food.getPrice());
        DecimalFormat df = new DecimalFormat("#,###");
        String formatted;
        if (price < 1000) {
            df = new DecimalFormat("###");
        }
        if (price > 1000 && price < 10000) {
            df = new DecimalFormat("#,###");
        }
        if (price >= 10000) {
            df = new DecimalFormat("##,###");
        }
        formatted = df.format(price);
        holder.txt_price.setText(formatted);

        //logic for spicy foods
        if (food.getCategoryId().equals("3")) {
            holder.food_spicy.setVisibility(View.VISIBLE);
            holder.onBindSpicy(Integer.parseInt(key));
            holder.food_spicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!itemStateArray.get(Integer.parseInt(key), false)) {
                        holder.food_spicy.setBackground(mContext.getResources().getDrawable(R.drawable.bc_spicy_yellow));
                        itemStateArray.put(Integer.parseInt(key), true);
                        mFoodList.get(holder.getAdapterPosition()).setSpicy(true);
                    } else {
                        holder.food_spicy.setBackground(mContext.getResources().getDrawable(R.drawable.bc_spicy_white));
                        itemStateArray.put(Integer.parseInt(key), false);
                        mFoodList.get(holder.getAdapterPosition()).setSpicy(false);
                    }
                }
            });
        }
        else {
            holder.food_spicy.setVisibility(View.GONE);
        }

        holder.txt_name.setText(food.getTitle());

        //logic for button click
        holder.bind(key);
        holder.btn_oook.setOnClickListener(new View.OnClickListener() {

            private boolean unchecked;

            @Override
            public void onClick(View v) {

                if (!sharedPreferences.getBoolean(key, false)) {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_yellow));
                    foodSelectListener.onFoodSelected(mFoodList.get(holder.getAdapterPosition()), 1);
                } else {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_white));
                    foodSelectListener.onFoodSelected(mFoodList.get(holder.getAdapterPosition()), 0);
                }
            }
        });

        Glide.with(mContext).load(mFoodList.get(position).getThumbnail()).into(holder.image_food);
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_price)
        TextView txt_price;

        @BindView(R.id.food_name)
        TextView txt_name;

        @BindView(R.id.btn_oook)
        Button btn_oook;

        @BindView(R.id.food_image)
        ImageView image_food;

        @BindView(R.id.food_spicy)
        ImageView food_spicy;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //while binding for the selected items state
        public void bind(String key) {

            if (!sharedPreferences.getBoolean(key, false)) {
                btn_oook.setBackground(itemView.getResources().getDrawable(R.drawable.btn_ok_white));
            } else {
                btn_oook.setBackground(itemView.getResources().getDrawable(R.drawable.btn_ok_yellow));
            }


        }

        public void onBindSpicy(int key) {
            if (!itemStateArray.get(key, false)) {
                food_spicy.setBackground(itemView.getResources().getDrawable(R.drawable.bc_spicy_white));
            } else {
                food_spicy.setBackground(itemView.getResources().getDrawable(R.drawable.bc_spicy_yellow));
            }
        }

    }
}
