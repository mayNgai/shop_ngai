package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.util.Log;

import com.may.maystream.shopngai.fragment.HomeFragment;
import com.may.maystream.shopngai.model.TblCategory;
import com.may.maystream.shopngai.model.Tbldiscount;
import com.may.maystream.shopngai.service.ForumService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by May on 8/23/2017.
 */

public class HomePresenter {
    ProgressDialog dialog;
    HomeFragment mView;
    ForumService mForum;

    public HomePresenter(HomeFragment view, ForumService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadCategory() {
        dialog = ProgressDialog.show(mView.getContext(), "Wait", "loading for you...");
        mForum.getApi()
                .getCategory()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TblCategory>>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("categoryCall Error", e.getMessage());
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(List<TblCategory> posts) {
                        mView.displayPosts(posts);
                        dialog.dismiss();
                    }
                });
    }

    public void loadDiscount() {
//        dialog = ProgressDialog.show(mView.getContext(), "Wait", "loading for you...");
        mForum.getApi()
                .getDiscount()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Tbldiscount>>() {
                    @Override
                    public void onCompleted() {
//                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("categoryCall Error", e.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(List<Tbldiscount> posts) {
                        mView.displayDiscount(posts);
//                        dialog.dismiss();
                    }
                });
    }
}
