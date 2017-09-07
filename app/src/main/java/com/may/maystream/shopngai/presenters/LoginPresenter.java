package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.may.maystream.shopngai.activity.LoginActivity;
import com.may.maystream.shopngai.activity.MainActivity;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.service.ApiService;

import java.util.UUID;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by May on 8/23/2017.
 */

public class LoginPresenter {
    ProgressDialog dialog;
    ApiService mForum;
    LoginActivity mView;

    public LoginPresenter(LoginActivity view, ApiService forum) {

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
                        updateLogin(member);
//                        dialog.dismiss();
                    }
                });
    }

    public void updateLogin(TblMember member){
        try {
            if(member.getSuccess().equalsIgnoreCase("0")){
                Toast.makeText(mView.getApplicationContext(), member.getMessage(), Toast.LENGTH_SHORT).show();
            }else if(member.getSuccess().equalsIgnoreCase("1")){
                TblMember t = new TblMember();
                t.setGuid(UUID.randomUUID().toString());
                t.setAuthentication(member.getAuthentication());
                t.setDate_register(member.getDate_register());
                t.setEmail(member.getEmail());
                t.setFirst_name(member.getFirst_name());
                t.setLast_name(member.getLast_name());
                t.setStatus(member.getStatus());
                t.setTel(member.getTel());
                t.setUser_id(member.getUser_id());
                t.setPassword("");
                t.setLanguage("en");
                t.setSuccess(member.getSuccess());
                t.setMessage(member.getMessage());
                t.setId(member.getId());
                TaskController taskController = new TaskController();
                taskController.createMember(t);
                Intent i = new Intent(mView.getApplicationContext(),MainActivity.class);
                mView.startActivity(i);
                mView.finish();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
