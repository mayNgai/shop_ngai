package com.may.maystream.shopngai.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.adapter.AddImageAdapter;
import com.may.maystream.shopngai.model.Action;
import com.may.maystream.shopngai.model.TblPicture;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by may on 9/6/2017.
 */

public class AddOrderActivity extends AppCompatActivity{
    private Spinner spn_category,spn_condition;
    private RecyclerView mRecyclerView;
    private List<TblPicture> imagePaths;
    private TblPicture pics;
    private AddImageAdapter mAdapter;
    private ImageView imgView;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imagePaths = new ArrayList<TblPicture>();
        pics = new TblPicture();
        initImageLoader();
        openGalleryMullti();
        init();
        setCategory();
        setCondition();

    }
    private void initImageLoader() {
        try {
            String CACHE_DIR = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/.temp_tmp";
            new File(CACHE_DIR).mkdirs();

            File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
                    CACHE_DIR);

            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
            ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                    getBaseContext())
                    .defaultDisplayImageOptions(defaultOptions)
                    .discCache(new UnlimitedDiscCache(cacheDir))
                    .memoryCache(new WeakMemoryCache());

            ImageLoaderConfiguration config = builder.build();
            imageLoader = ImageLoader.getInstance();
            imageLoader.init(config);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void init() {
        try {
            imgView = (ImageView)findViewById(R.id.imgView);
            spn_category = (Spinner)findViewById(R.id.spn_category);
            spn_condition = (Spinner)findViewById(R.id.spn_condition);
            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_image_view);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new AddImageAdapter(this,imagePaths,imageLoader);
            mRecyclerView.setAdapter(mAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openGallery(){
        Intent i = new Intent(Action.ACTION_PICK);
        startActivityForResult(i, 100);
    }

    private void openGalleryMullti(){
        Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
        startActivityForResult(i, 200);
    }

    private void setCategory(){
        String[] category = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, category);
        spn_category.setAdapter(adapterCategory);
    }

    private void setCondition(){
        String[] condition = getResources().getStringArray(R.array.condition);
        ArrayAdapter<String> adapterCondition = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, condition);
        spn_condition.setAdapter(adapterCondition);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_order, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                this.finish();
                return true;
            case R.id.action_submit:
//                myAccountPresenter.updateProfile();
                Toast.makeText(this, "Submit", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        // your code.
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String single_path = data.getStringExtra("single_path");
//            imageLoader.displayImage("file://" + single_path, imgView);
            String[] name_pic = single_path.split("/");
            int a = name_pic.length;
            pics.setGuid(UUID.randomUUID().toString());
            pics.setName(name_pic[a-1]);
            pics.setPath(single_path);
            imagePaths.add(pics);
            pics = new TblPicture();
            pics.setGuid(UUID.randomUUID().toString());
            imagePaths.add(pics);
            mAdapter = new AddImageAdapter(this,imagePaths,imageLoader);
            mRecyclerView.setAdapter(mAdapter);
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");
            for (String string : all_path) {
                pics = new TblPicture();
                String[] name_pic = string.split("/");
                int a = name_pic.length;
                pics.setGuid(UUID.randomUUID().toString());
                pics.setName(name_pic[a-1]);
                pics.setPath(string);
                imagePaths.add(pics);
            }
            pics = new TblPicture();
            pics.setGuid(UUID.randomUUID().toString());
            imagePaths.add(pics);
            mAdapter = new AddImageAdapter(this,imagePaths,imageLoader);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
