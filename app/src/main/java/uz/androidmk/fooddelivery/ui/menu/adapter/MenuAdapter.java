package uz.androidmk.fooddelivery.ui.menu.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.model.Menu;
import uz.androidmk.fooddelivery.ui.food.FoodActivity;

/**
 * Created by Azamat on 8/6/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    ArrayList<Menu> mMenuList;

    public MenuAdapter(ArrayList<Menu> list) {
        mMenuList = list;
    }

    public void addItems(ArrayList<Menu> list){
        if(mMenuList.isEmpty())
            mMenuList.clear();
        mMenuList.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdapter.ViewHolder holder, final int position) {
        holder.category_title.setText(mMenuList.get(position).getMenuTitle());

        Glide.with(holder.itemView.getContext()).asBitmap().load(mMenuList.get(position).getMenuThumbnail()).into(holder.category_image);
//        holder.category_image.setImageResource(mMenuList.get(position).getImage());

        holder.cardView.setPreventCornerOverlap(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent food = new Intent(holder.itemView.getContext(), FoodActivity.class);
                food.putExtra("menuId",mMenuList.get(position).getMenuId());
                holder.itemView.getContext().startActivity(food);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mMenuList != null){
          Log.d("Tagii", "onBindViewHolder: " + mMenuList.size());
            return mMenuList.size();
        }
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.category_title)
        TextView category_title;

        //        @BindView(R.id.category_image)
        ImageView category_image;

        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView.getContext());
            category_title = itemView.findViewById(R.id.category_title);
            category_image = itemView.findViewById(R.id.category_image);
            cardView = itemView.findViewById(R.id.main_cardview);

        }
    }
}
