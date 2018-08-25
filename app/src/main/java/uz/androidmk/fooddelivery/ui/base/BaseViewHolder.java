package uz.androidmk.fooddelivery.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Azamat on 8/24/2018.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    // clear is used because recycler view REUSES the view
    // if it is not used, it will show previous valueus
    protected abstract void clear();

    public void onBind(int position){
        mCurrentPosition = position;
        clear(); // needs to be called
    }

    public int getCurrentPosition(){
        return mCurrentPosition;
    }
}
