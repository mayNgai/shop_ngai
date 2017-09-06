package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.util.Log;

import com.may.maystream.shopngai.activity.SellingActivity;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.service.ApiService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by May on 8/28/2017.
 */

public class SellingPresenter {
    ProgressDialog dialog;
    SellingActivity mView;
    ApiService mForum;

    public SellingPresenter(SellingActivity view, ApiService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadMyItem(String id) {
        dialog = ProgressDialog.show(mView, "Wait", "loading for you...");
        mForum.getApi()
                .getMyItem(Integer.parseInt(id))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TblMyItem>>() {
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
                    public void onNext(List<TblMyItem> posts) {
                        mView.displayItem(posts);
                        dialog.dismiss();
                    }
                });
    }
}
