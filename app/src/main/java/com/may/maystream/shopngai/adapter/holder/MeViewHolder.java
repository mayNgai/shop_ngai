package com.may.maystream.shopngai.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.may.maystream.shopngai.R;

/**
 * Created by May on 8/21/2017.
 */

public class MeViewHolder extends RecyclerView.ViewHolder{
    public ImageView img_icon;
    public TextView txtName;
    public MeViewHolder(View itemView) {
        super(itemView);
        img_icon = (ImageView)itemView.findViewById(R.id.img_icon);
        txtName= (TextView)itemView.findViewById(R.id.txtName);
    }
}
