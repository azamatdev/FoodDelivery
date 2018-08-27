package uz.androidmk.fooddelivery.ui.food.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import uz.androidmk.fooddelivery.model.Food;
import uz.androidmk.fooddelivery.ui.base.BaseViewHolder;

/**
 * Created by Azamat on 8/10/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private String TAG = "FoodAdapter";
    private ArrayList<Food> mFoodList;
    private FoodSelectListener foodSelectListener;

    // to keep the current state of the selected item
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    public void setFoodSelectListener(FoodSelectListener callback) {
        this.foodSelectListener = callback;
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

        final int key =Integer.parseInt(food.getKey());
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

        holder.txt_name.setText(food.getTitle());

        holder.bind(key);
        holder.btn_oook.setOnClickListener(new View.OnClickListener() {

            private boolean unchecked;

            @Override
            public void onClick(View v) {
                if (!itemStateArray.get(key, false)) {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_yellow));
                    itemStateArray.put(key, true);
                    Toast.makeText(mContext, "Checked" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                } else {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_white));
                    itemStateArray.put(key, false);
                    Toast.makeText(mContext, "unchecked" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
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

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //while binding for the selected items state
        public void bind(int key){
            if(!itemStateArray.get(key,false)){
                btn_oook.setBackground(itemView.getResources().getDrawable(R.drawable.btn_ok_white));
            }
            else {
                btn_oook.setBackground(itemView.getResources().getDrawable(R.drawable.btn_ok_yellow));
            }
        }

    }
}
