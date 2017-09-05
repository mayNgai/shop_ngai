package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.util.Log;

import com.may.maystream.shopngai.activity.MyAccountActivity;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.service.ForumService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by may on 9/5/2017.
 */

public class MyAccountPresenter {
    ProgressDialog dialog;
    ForumService mForum;
    MyAccountActivity mView;

    public MyAccountPresenter(MyAccountActivity view,ForumService forum){
        mView = view;
        mForum = forum;
    }

    public void updateProfile() {
//        dialog = ProgressDialog.show(mView.getApplicationContext(), "Wait", "loading ...");
        mForum.getApi()
                .getLogin("","")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TblMember>() {
                    @Override
                    public void onCompleted() {
//                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("updateProfile Error", e.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(TblMember member) {
                        mView.updateProfile(member);
//                        dialog.dismiss();
                    }
                });
    }

}
