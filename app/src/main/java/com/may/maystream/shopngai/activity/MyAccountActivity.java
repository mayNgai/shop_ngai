package com.may.maystream.shopngai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.presenters.MyAccountPresenter;
import com.may.maystream.shopngai.service.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 9/5/2017.
 */

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText input_first_name,input_last_name,input_tel,input_mail;
    private ImageView imgEditFirstName,imgEditLastName,imgEditTel,imgEditEmail,imgSuccessFirstName,imgSuccessLastName,imgSuccessTel,imgSuccessEmail;
    private Button btnLogOut;
    private TaskController taskController;
    public List<TblMember> tblMembers;

    private ApiService mApiService;
    private MyAccountPresenter myAccountPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskController = new TaskController();
        init();
        setDefault();
        mApiService = new ApiService();
        myAccountPresenter = new MyAccountPresenter(this, mApiService);
    }

    private void init(){
        input_first_name = (EditText)findViewById(R.id.input_first_name);
        input_last_name = (EditText)findViewById(R.id.input_last_name);
        input_tel = (EditText)findViewById(R.id.input_tel);
        input_mail = (EditText)findViewById(R.id.input_mail);
        imgEditFirstName = (ImageView)findViewById(R.id.imgEditFirstName);
        imgEditLastName = (ImageView)findViewById(R.id.imgEditLastName);
        imgEditTel = (ImageView)findViewById(R.id.imgEditTel);
        imgEditEmail = (ImageView)findViewById(R.id.imgEditEmail);
        imgSuccessFirstName = (ImageView)findViewById(R.id.imgSuccessFirstName);
        imgSuccessLastName = (ImageView)findViewById(R.id.imgSuccessLastName);
        imgSuccessTel = (ImageView)findViewById(R.id.imgSuccessTel);
        imgSuccessEmail = (ImageView)findViewById(R.id.imgSuccessEmail);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        imgEditFirstName.setOnClickListener(this);
        imgEditLastName.setOnClickListener(this);
        imgEditTel.setOnClickListener(this);
        imgEditEmail.setOnClickListener(this);
        imgSuccessFirstName.setOnClickListener(this);
        imgSuccessLastName.setOnClickListener(this);
        imgSuccessTel.setOnClickListener(this);
        imgSuccessEmail.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    public void setText(){
        tblMembers = new ArrayList<TblMember>();
        tblMembers = taskController.checkMember();
        input_first_name.setText(tblMembers.get(0).getFirst_name());
        input_last_name.setText(tblMembers.get(0).getLast_name());
        input_tel.setText(tblMembers.get(0).getTel());
        input_mail.setText(tblMembers.get(0).getEmail());
    }

    public void setDefault(){
        input_first_name.setEnabled(false);
        input_last_name.setEnabled(false);
        input_tel.setEnabled(false);
        input_mail.setEnabled(false);
        imgEditFirstName.setVisibility(View.VISIBLE);
        imgEditLastName.setVisibility(View.VISIBLE);
        imgEditTel.setVisibility(View.VISIBLE);
        imgEditEmail.setVisibility(View.VISIBLE);
        imgSuccessFirstName.setVisibility(View.GONE);
        imgSuccessLastName.setVisibility(View.GONE);
        imgSuccessTel.setVisibility(View.GONE);
        imgSuccessEmail.setVisibility(View.GONE);
        setText();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgEditFirstName:
                input_first_name.setEnabled(true);
                imgEditFirstName.setVisibility(View.GONE);
                imgSuccessFirstName.setVisibility(View.VISIBLE);
                break;
            case R.id.imgEditLastName:
                input_last_name.setEnabled(true);
                imgEditLastName.setVisibility(View.GONE);
                imgSuccessLastName.setVisibility(View.VISIBLE);
                break;
            case R.id.imgEditTel:
                input_tel.setEnabled(true);
                imgEditTel.setVisibility(View.GONE);
                imgSuccessTel.setVisibility(View.VISIBLE);
                break;
            case R.id.imgEditEmail:
                input_mail.setEnabled(true);
                imgEditEmail.setVisibility(View.GONE);
                imgSuccessEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.imgSuccessFirstName:
                input_first_name.setEnabled(false);
                imgSuccessFirstName.setVisibility(View.GONE);
                imgEditFirstName.setVisibility(View.VISIBLE);
                break;
            case R.id.imgSuccessLastName:
                input_last_name.setEnabled(false);
                imgSuccessLastName.setVisibility(View.GONE);
                imgEditLastName.setVisibility(View.VISIBLE);
                break;
            case R.id.imgSuccessTel:
                input_tel.setEnabled(false);
                imgSuccessTel.setVisibility(View.GONE);
                imgEditTel.setVisibility(View.VISIBLE);
                break;
            case R.id.imgSuccessEmail:
                input_mail.setEnabled(false);
                imgSuccessEmail.setVisibility(View.GONE);
                imgEditEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.btnLogOut:
                myAccountPresenter.logOut();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_save:
                myAccountPresenter.updateProfile();
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

}
