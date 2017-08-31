package com.may.maystream.shopngai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.activity.LoginActivity;
import com.may.maystream.shopngai.activity.SignUpActivity;
import com.may.maystream.shopngai.adapter.MeAdapter;
import com.may.maystream.shopngai.adapter.model.BaseMeItem;
import com.may.maystream.shopngai.controller.TaskController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 8/18/2017.
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private Button btn_login,btn_sign_up;
    private LinearLayout lay_group_login;
    private TaskController controller;
    private RecyclerView recyclerView;
    private MeAdapter meAdapter;

    public static  MeFragment newInstance(){
        MeFragment fragment = new MeFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        btn_login = (Button)rootView.findViewById(R.id.btn_login);
        btn_sign_up = (Button)rootView.findViewById(R.id.btn_sign_up);
        lay_group_login = (LinearLayout)rootView.findViewById(R.id.lay_group_login);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        btn_login.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);

        isLogin();

        return rootView;
    }

    private void setupView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<BaseMeItem> baseMeItems = new ArrayList<BaseMeItem>();
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            input.add("test");
        }
        meAdapter = new MeAdapter(input);
        recyclerView.setAdapter(meAdapter);
    }

    private void isLogin(){
        try {
            controller = new TaskController();
            if(AccessToken.getCurrentAccessToken() != null || controller.checkMember().size()>0) {
                lay_group_login.setVisibility(View.GONE);
                setupView();
            }else {
                lay_group_login.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_login:
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);

                break;
            case R.id.btn_sign_up:
                Intent a = new Intent(getActivity(), SignUpActivity.class);
                startActivity(a);

                break;
        }

    }
}
