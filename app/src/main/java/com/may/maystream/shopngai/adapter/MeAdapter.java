package com.may.maystream.shopngai.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.activity.MyAccountActivity;
import com.may.maystream.shopngai.activity.SellingActivity;
import com.may.maystream.shopngai.adapter.holder.MeViewHolder;

import java.util.List;

/**
 * Created by May on 8/21/2017.
 */

public class MeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> list;
    public MeAdapter(List<String>_list) {
        this.list = _list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_me, parent, false);
        return new MeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        final MeViewHolder meViewHolder =(MeViewHolder)holder;
        if(position == 0){
            meViewHolder.txtName.setText(R.string.txt_my_address);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_account_circle_black_24dp);
        }else if(position == 1){
            meViewHolder.txtName.setText(R.string.txt_noti);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_notifications_black_24dp);
        }else if(position == 2){
            meViewHolder.txtName.setText(R.string.txt_buying);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_home_black_24dp);
        }else if(position == 3){
            meViewHolder.txtName.setText(R.string.txt_selling);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_gavel_black_24dp);
        }else if(position == 4){
            meViewHolder.txtName.setText(R.string.txt_my_rating);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_grade_black_24dp);
        }else if(position == 5){
            meViewHolder.txtName.setText(R.string.txt_my_like);
            meViewHolder.img_icon.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        meViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(meViewHolder.itemView.getContext(), "Click List" + position, Toast.LENGTH_SHORT)
                        .show();
                if(position == 0){
                    Intent i = new Intent(meViewHolder.itemView.getContext(), MyAccountActivity.class);
                    meViewHolder.itemView.getContext().startActivity(i);
                }else if(position == 1){

                }else if(position == 2){

                }else if(position == 3){
                    Intent i = new Intent(meViewHolder.itemView.getContext(), SellingActivity.class);
                    meViewHolder.itemView.getContext().startActivity(i);

                }else if(position == 4){

                }else if(position == 5){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
