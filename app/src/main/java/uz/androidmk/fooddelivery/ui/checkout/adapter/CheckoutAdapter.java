package uz.androidmk.fooddelivery.ui.checkout.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.model.Food;

/**
 * Created by Azamat on 8/28/2018.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

    ArrayList<Food> foodList;

    public CheckoutAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (!foodList.isEmpty())
            return foodList.size();
        else
            return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.checkout_food_title)
        TextView foodTitle;

        @BindView(R.id.checkout_food_price)
        TextView foodPrice;

        @BindView(R.id.checkout_delete)
        ImageView foodDelete;

        @BindView(R.id.elegant_number)
        ElegantNumberButton elegantNumberButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
