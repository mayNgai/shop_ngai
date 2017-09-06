package com.may.maystream.shopngai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.service.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by May on 8/28/2017.
 */

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder>{
    private List<TblMyItem> mItems;
    private ApiService mForum;
    private Context context;

    public MyItemAdapter(Context _context) {
        super();
        mItems = new ArrayList<TblMyItem>();
        this.context = _context;
    }
    public void addData(List<TblMyItem> myItems) {
        mItems.addAll(myItems);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_selling, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TblMyItem myItem = mItems.get(i);
        viewHolder.name.setText(myItem.getO_name());
        viewHolder.price.setText(myItem.getO_price());
        viewHolder.like.setText(myItem.getO_like());

        String url = mForum.FORUM_SERVER_URL + "/img/" + myItem.getO_pic_name();
        Picasso.with(context)
                .load(url)
                //.resize(100, 100)
                //.centerCrop()
//                .error(R.drawable.)
                .into(viewHolder.img_ic);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,like;
        public ImageView img_ic;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            like = (TextView) itemView.findViewById(R.id.like);
            img_ic = (ImageView) itemView.findViewById(R.id.img_ic);
        }
    }
}
