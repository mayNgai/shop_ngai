package com.may.maystream.shopngai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.adapter.MyItemAdapter;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.presenters.SellingPresenter;
import com.may.maystream.shopngai.service.ApiService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by May on 8/28/2017.
 */

public class SellingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyItemAdapter mMyItemAdapter;
    private ApiService mApiService;
    private SellingPresenter mSellingPresenter;
    private TaskController controller;
    private List<TblMember> member;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mMyItemAdapter = new MyItemAdapter(SellingActivity.this);
        mRecyclerView.setAdapter(mMyItemAdapter);

        controller = new TaskController();
        member = new ArrayList<TblMember>();
        member = controller.checkMember();
        if(member.size()>0){
            mApiService = new ApiService();
            mSellingPresenter = new SellingPresenter(SellingActivity.this, mApiService);
            mSellingPresenter.loadMyItem(member.get(0).getId());
        }


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
