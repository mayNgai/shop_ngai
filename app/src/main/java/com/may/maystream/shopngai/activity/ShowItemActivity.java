package com.may.maystream.shopngai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.adapter.MyItemAdapter;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.presenters.ShowItemPresenter;
import com.may.maystream.shopngai.service.ForumService;

import java.util.List;

/**
 * Created by May on 8/28/2017.
 */

public class ShowItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyItemAdapter mMyItemAdapter;
    private ForumService mForumService;
    private ShowItemPresenter mShowItemPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mMyItemAdapter = new MyItemAdapter(ShowItemActivity.this);
        mRecyclerView.setAdapter(mMyItemAdapter);

        mForumService = new ForumService();
        mShowItemPresenter = new ShowItemPresenter(ShowItemActivity.this, mForumService);
        mShowItemPresenter.loadItem("");
    }
    public void displayItem(List<TblMyItem> myItems) {

        mMyItemAdapter.clear();
        mMyItemAdapter.addData(myItems);
    }
}
