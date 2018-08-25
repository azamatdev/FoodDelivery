package uz.androidmk.fooddelivery.ui.food.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private boolean isChecked= true;

    public FoodAdapter(ArrayList<Food> mList) {
        this.mFoodList = mList;
    }

    public void addItems(ArrayList<Food> foods){
        Log.d(TAG, "addItems: ");
        if(!mFoodList.isEmpty())
            mFoodList.clear();
        mFoodList.addAll(foods);
//        mFoodList = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: ");
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: ");
        return position;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mFoodList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final Context mContext = holder.itemView.getContext();
        int price = Integer.parseInt(mFoodList.get(position).getPrice());
        DecimalFormat df = new DecimalFormat("#,###");
        String formatted;
        if(price < 1000)
        {
            df = new DecimalFormat("###");
        }
        if(price > 1000 && price < 10000)
        {
            df = new DecimalFormat("#,###");
        }
        if(price >= 10000)
        {
            df = new DecimalFormat("##,###");
        }
        formatted = df.format(price);
        holder.txt_price.setText(formatted);

        holder.txt_name.setText(mFoodList.get(position).getTitle());

        holder.btn_oook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isChecked) {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_yellow));
//                    holder.btn_oook.setTextColor(mContext.getResources().getColor(R.color.background));
                    isChecked = false;
                    Toast.makeText(mContext, "Checked" + position + "\n " + getItemViewType(position), Toast.LENGTH_SHORT).show();
                }else
                {
                    holder.btn_oook.setBackground(mContext.getResources().getDrawable(R.drawable.btn_ok_white));
//                    holder.btn_oook.setTextColor(mContext.getResources().getColor(R.color.activeColorGreen));
                    isChecked = true;
                    Toast.makeText(mContext, "unchecked" + position + "\n " + getItemViewType(position), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(holder.itemView.getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(mContext).load(mFoodList.get(position).getThumbnail()).into(holder.image_food);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
            Log.d(TAG, "ViewHolder: ");
        }

    }
}
