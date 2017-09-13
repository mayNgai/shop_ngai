package com.may.maystream.shopngai.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.adapter.HomeAdapter;
import com.may.maystream.shopngai.adapter.SlidingImage_Adapter;
import com.may.maystream.shopngai.model.TblCategory;
import com.may.maystream.shopngai.model.Tbldiscount;
import com.may.maystream.shopngai.presenters.HomePresenter;
import com.may.maystream.shopngai.service.ApiService;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by may on 8/18/2017.
 */

public class HomeFragment extends Fragment {
    private static ViewPager mPager;
    private CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private List<Tbldiscount> ImagesArray;
    private HomeAdapter mHomeAdapter;
    private ApiService mApiService;
    private HomePresenter mHomePresenter;
    private RecyclerView mRecyclerView;
    public static  HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        indicator = (CirclePageIndicator)rootView.findViewById(R.id.indicator);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        recyclerViewLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mHomeAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mHomeAdapter);

        mApiService = new ApiService();
        mHomePresenter = new HomePresenter(this, mApiService);
        mHomePresenter.loadCategory();
        mHomePresenter.loadDiscount();

        return rootView;
    }


    public void displayPosts(List<TblCategory> category) {

        mHomeAdapter.clear();
        mHomeAdapter.addData(category);
    }

    public void displayDiscount(List<Tbldiscount> discount) {
        ImagesArray = new ArrayList<Tbldiscount>();
        ImagesArray.addAll(discount);
        mPager.setAdapter(new SlidingImage_Adapter(getContext(),ImagesArray));
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        NUM_PAGES =ImagesArray.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 5000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
}
