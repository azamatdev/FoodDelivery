package uz.androidmk.fooddelivery.ui.food.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import uz.androidmk.fooddelivery.R;
import uz.androidmk.fooddelivery.data.model.Menu;
import uz.androidmk.fooddelivery.ui.food.FoodMvpView;

/**
 * Created by Azamat on 8/6/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private String TAG = "CategoryAdapter";

    ArrayList<Menu> mMenuList;

    FoodMvpView callBack;

    FoodSelectListener foodSelectListener;

    public CategoryAdapter(ArrayList<Menu> list) {
        mMenuList = list;
    }

    public void setFoodSelectListener(FoodSelectListener foodSelectListener) {
        this.foodSelectListener = foodSelectListener;
    }

    public void setCallBack(FoodMvpView callBack) {
        this.callBack = callBack;
    }

    public void addItems(ArrayList<Menu> list) {
        if (mMenuList.isEmpty())
            mMenuList.clear();
        mMenuList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, final int position) {
        holder.category_title.setText(mMenuList.get(position).getMenuTitle());

        Glide.with(holder.itemView.getContext()).asBitmap().load(mMenuList.get(position).getMenuThumbnail()).into(holder.category_image);
//        holder.category_image.setImageResource(mMenuList.get(position).getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodSelectListener.onCategorySelected(position + 1);
//                DatabaseReference newList = FirebaseDatabase.getInstance().getReference();
//                newList.child("Food")
//                        .orderByChild("categoryId")
//                        .equalTo(mMenuList.get(position).getMenuId()).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Log.d(TAG, "onDataChange: ");
//                        ArrayList<Food> newFoodList = new ArrayList<>();
//                        Log.d("FoodTag", "onDataChange: " + dataSnapshot.getChildrenCount());
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            Log.d("FoodTag", "onDataChange: " + ds.getChildren());
//                            Food food = new Food();
//                            food.setPrice(ds.child("price").getValue().toString());
//                            food.setTitle(ds.child("title").getValue().toString());
//                            food.setThumbnail(ds.child("thumbnail").getValue().toString());
//                            newFoodList.add(food);
//                        }
//                        foodSelectListener.onCategorySelected(newFoodList);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Log.d("FoodTag", "onCancelled: " + databaseError.getDetails());
//                    }
//                });
//                Intent food = new Intent(holder.itemView.getContext(), FoodActivity.class);
//                food.putExtra("menuId",mMenuList.get(position).getMenuId());
//                holder.itemView.getContext().startActivity(food);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mMenuList != null) {
            return mMenuList.size();
        } else
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
            ButterKnife.bind(this, itemView);
            category_title = itemView.findViewById(R.id.selected_category_name);
            category_image = itemView.findViewById(R.id.selected_category_image);
        }
    }
}
