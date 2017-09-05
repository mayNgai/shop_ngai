package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.util.Log;

import com.may.maystream.shopngai.activity.LoginActivity;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.service.ForumService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by May on 8/23/2017.
 */

public class LoginPresenter {
    ProgressDialog dialog;
    ForumService mForum;
    LoginActivity mView;

    public LoginPresenter(LoginActivity view, ForumService forum) {

        mView = view;
        mForum = forum;
    }

    public void loadLogin() {
//        dialog = ProgressDialog.show(mView.getApplicationContext(), "Wait", "loading for you...");
        mForum.getApi()
                .getLogin(mView.strEmail,mView.strPassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TblMember>() {
                    @Override
                    public void onCompleted() {
//                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("login Error", e.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(TblMember member) {
                        mView.updateLogin(member);
//                        dialog.dismiss();
                    }
                });
    }

}
