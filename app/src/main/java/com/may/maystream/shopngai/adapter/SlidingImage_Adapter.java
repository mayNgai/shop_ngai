package com.may.maystream.shopngai.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.model.Tbldiscount;
import com.may.maystream.shopngai.service.ForumService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by May on 8/23/2017.
 */

public class SlidingImage_Adapter extends PagerAdapter {
    private List<Tbldiscount> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private ForumService mForum;

    public SlidingImage_Adapter(Context context, List<Tbldiscount> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.vp_image, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        String url = mForum.FORUM_SERVER_URL + "/img/" + IMAGES.get(position).getD_name_pic();
        Picasso.with(context)
                .load(url)
                .resize(200, 70)
                .centerCrop()
//                .error(R.drawable.)
                .into(imageView);
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
