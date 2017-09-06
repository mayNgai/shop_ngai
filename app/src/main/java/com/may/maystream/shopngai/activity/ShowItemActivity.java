package com.may.maystream.shopngai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.adapter.MyItemAdapter;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.presenters.ShowItemPresenter;
import com.may.maystream.shopngai.service.ApiService;

import java.util.List;

/**
 * Created by May on 8/28/2017.
 */

public class ShowItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyItemAdapter mMyItemAdapter;
    private ApiService mApiService;
    private ShowItemPresenter mShowItemPresenter;
    private String type = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent extra = getIntent();
        if (extra != null) {
            if(extra.getStringExtra("type")!=null)
                type = extra.getStringExtra("type");
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mMyItemAdapter = new MyItemAdapter(ShowItemActivity.this);
        mRecyclerView.setAdapter(mMyItemAdapter);

        mApiService = new ApiService();
        mShowItemPresenter = new ShowItemPresenter(ShowItemActivity.this, mApiService);
        mShowItemPresenter.loadItem(type);
    }
    public void displayItem(List<TblMyItem> myItems) {

        mMyItemAdapter.clear();
        mMyItemAdapter.addData(myItems);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
