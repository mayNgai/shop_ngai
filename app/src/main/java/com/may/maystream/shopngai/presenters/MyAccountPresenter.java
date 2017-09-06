package com.may.maystream.shopngai.presenters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.may.maystream.shopngai.activity.MainActivity;
import com.may.maystream.shopngai.activity.MyAccountActivity;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.service.ApiService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by may on 9/5/2017.
 */

public class MyAccountPresenter {
    private ProgressDialog dialog;
    private ApiService mForum;
    private MyAccountActivity mView;
    private TaskController taskController;
    public MyAccountPresenter(MyAccountActivity view,ApiService forum){
        mView = view;
        mForum = forum;
        taskController = new TaskController();

    }

    public void updateProfile() {
//        dialog = ProgressDialog.show(mView.getApplicationContext(), "Wait", "loading ...");
        mForum.getApi()
                .updateProfile(mView.tblMembers.get(0).getId(),mView.input_first_name.getText().toString().trim()
                        ,mView.input_last_name.getText().toString().trim(),mView.input_mail.getText().toString().trim(),mView.input_tel.getText().toString().trim())
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
                        updateProfile(member);
//                        dialog.dismiss();
                    }
                });
    }

    public void updateProfile(TblMember member){
        try {
            if(member.getSuccess().equalsIgnoreCase("0")){
                Toast.makeText(mView.getApplicationContext(), member.getMessage(), Toast.LENGTH_SHORT).show();
            }else if(member.getSuccess().equalsIgnoreCase("1")){
                mView.tblMembers.get(0).setAuthentication(member.getAuthentication());
                mView.tblMembers.get(0).setDate_register(member.getDate_register());
                mView.tblMembers.get(0).setEmail(member.getEmail());
                mView.tblMembers.get(0).setFirst_name(member.getFirst_name());
                mView.tblMembers.get(0).setLast_name(member.getLast_name());
                mView.tblMembers.get(0).setStatus(member.getStatus());
                mView.tblMembers.get(0).setTel(member.getTel());
                mView.tblMembers.get(0).setUser_id(member.getUser_id());
                mView.tblMembers.get(0).setPassword("");
                mView.tblMembers.get(0).setSuccess(member.getSuccess());
                mView.tblMembers.get(0).setMessage(member.getMessage());
                mView.tblMembers.get(0).setId(member.getId());
                taskController.updateMember(mView.tblMembers.get(0));
                mView.setDefault();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logOut(){
        try {
            taskController.deleteMember();
            Intent i = new Intent(mView.getApplicationContext(), MainActivity.class);
            mView.startActivity(i);
            mView.finish();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
