package uz.androidmk.fooddelivery.ui.cart.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.db.model.Food;

/**
 * Created by Azamat on 8/28/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<Food> foodList;
    private Callback callback;

    public CartAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }


    public void addItems(ArrayList<Food> foodList){
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Food food = foodList.get(position);
//        Log.d("TagAdapt", "onBindViewHolder: " + foodList.get(1).getTitle());
        Glide.with(holder.itemView.getContext()).load(food.getThumbnail()).into(holder.imageView);

        final int price = Integer.parseInt(food.getPrice());
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

        holder.foodPrice.setText(formatted);

        holder.totalFoodPrice.setText(formatted);

        holder.foodTitle.setText(food.getTitle());

        holder.foodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(food.getKey(), holder.getAdapterPosition());
            }
        });

        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                DecimalFormat newDf = new DecimalFormat("#,###");
                String newFormat;
                if (price < 1000) {
                    newDf = new DecimalFormat("###");
                }
                if (price > 1000 && price < 10000) {
                    newDf = new DecimalFormat("#,###");
                }
                if (price >= 10000) {
                    newDf = new DecimalFormat("##,###");
                }
                newFormat = newDf.format(price * newValue);
                foodList.get(holder.getAdapterPosition()).setPrice(Integer.toString(price*newValue));
                holder.totalFoodPrice.setText(newFormat);
                callback.updateUi(foodList);
            }
        });
    }

    private void removeItem(String key, int position){
        callback.itemRemoved(key);
        foodList.remove(position);
        callback.updateUi(foodList);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.checkout_food_title)
        TextView foodTitle;

        @BindView(R.id.checkout_food_price)
        TextView foodPrice;

        @BindView(R.id.total_food_price)
        TextView totalFoodPrice;

        @BindView(R.id.checkout_food_delete)
        ImageView foodDelete;

        @BindView(R.id.elegant_number)
        ElegantNumberButton elegantNumberButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
