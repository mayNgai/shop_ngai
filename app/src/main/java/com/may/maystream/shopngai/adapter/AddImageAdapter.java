package com.may.maystream.shopngai.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.model.TblPicture;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by May on 9/13/2017.
 */

public class AddImageAdapter extends RecyclerView.Adapter<AddImageAdapter.ViewHolder>{
    private List<TblPicture> arrayList;
    private Context mcontext;

    public AddImageAdapter(Context context, List<TblPicture> picture) {
        this.arrayList = picture;
        this.mcontext = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        if(i == 0)
            holder.textView.setText("Cover Photo");
        else
            holder.textView.setVisibility(View.GONE);
//        holder.imageView.setImageResource(arrayList.get(i).getPath());
//        String url = arrayList.get(i).getPath();
//        File f = new File(arrayList.get(i).getPath());
        if((arrayList.size()-1)!=i){
//            Uri uri = Uri.fromFile(new File(arrayList.get(i).getPath()));
            Picasso.with(mcontext)
                    .load("file://" + arrayList.get(i).getPath())
                    .error(R.drawable.com_facebook_profile_picture_blank_square)
                    .into(holder.imageView);
        }else {
            holder.imageView.setVisibility(View.GONE);
            holder.add.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public AddImageAdapter.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.item_list_add_image, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addAll(List<TblPicture> files) {

        try {
            this.arrayList.clear();
            this.arrayList.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView,add;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image);
            add = (TextView) v.findViewById(R.id.add);
        }
    }
}
