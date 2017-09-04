package com.may.maystream.shopngai.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.activity.ShowItemActivity;
import com.may.maystream.shopngai.model.TblCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by May on 8/22/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    List<TblCategory> mItems;

    public HomeAdapter() {
        super();
        mItems = new ArrayList<TblCategory>();
    }

    public void addData(List<TblCategory> category) {
        mItems.addAll(category);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_home, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int i) {
        TblCategory category = mItems.get(i);
        viewHolder.name.setText(category.getName());
        if(i==0)
            viewHolder.img_ic.setImageResource(R.drawable.ic_costume_black);
        else if(i==1)
            viewHolder.img_ic.setImageResource(R.drawable.ic_mobile_black);
        else if(i==2)
            viewHolder.img_ic.setImageResource(R.drawable.ic_camera_black);
        else if(i==3)
            viewHolder.img_ic.setImageResource(R.drawable.ic_laptop_black);
        else if(i==4)
            viewHolder.img_ic.setImageResource(R.drawable.ic_watch_black);
        else if(i==5)
            viewHolder.img_ic.setImageResource(R.drawable.ic_bike_black);
        else if(i==6)
            viewHolder.img_ic.setImageResource(R.drawable.ic_toys_black);
        else if(i==7)
            viewHolder.img_ic.setImageResource(R.drawable.ic_travel_black);
        else if(i==8)
            viewHolder.img_ic.setImageResource(R.drawable.ic_game_black);
        else if(i==9)
            viewHolder.img_ic.setImageResource(R.drawable.ic_books_black);
//        viewHolder.repos.setText("repos: " + github.getPublicRepos());
//        viewHolder.blog.setText("blog: " + github.getBlog());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(viewHolder.itemView.getContext(), "Click List" + i, Toast.LENGTH_SHORT)
//                        .show();
                int type = i+1;
                Intent intent = new Intent(viewHolder.itemView.getContext(), ShowItemActivity.class);
                intent.putExtra("type",String.valueOf(type));
                viewHolder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView img_ic;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            img_ic = (ImageView) itemView.findViewById(R.id.img_ic);
        }
    }
}
