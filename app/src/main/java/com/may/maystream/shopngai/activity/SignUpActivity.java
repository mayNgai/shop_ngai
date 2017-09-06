package com.may.maystream.shopngai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.presenters.SignUpPresenter;
import com.may.maystream.shopngai.service.ApiService;

/**
 * Created by May on 8/18/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input_first_name,input_last_name,input_email,input_phone,input_password,input_comfirm_password;
    private Button btn_signup;
    private LinearLayout lay_group_password;
    public String str_id = "",str_first_name  = "",str_last_name  = "",str_email  = "",
            str_phone  = "",str_password  = "",str_comfirm_password  = "",str_authen = "",str_status = "";
    private ApiService mApiService;
    private SignUpPresenter mSignUpPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent extra = getIntent();
        if (extra != null) {
            if(extra.getStringExtra("id")!=null)
                str_id = extra.getStringExtra("id");
            if(extra.getStringExtra("first_name")!=null)
                str_first_name = extra.getStringExtra("first_name");
            if(extra.getStringExtra("last_name")!=null)
                str_last_name = extra.getStringExtra("last_name");
            if(extra.getStringExtra("email")!=null)
                str_email = extra.getStringExtra("email");
        }


        init();
    }

    private void init(){
        input_first_name = (EditText)findViewById(R.id.input_first_name);
        input_last_name = (EditText)findViewById(R.id.input_last_name);
        input_email = (EditText)findViewById(R.id.input_email);
        input_phone = (EditText)findViewById(R.id.input_phone);
        input_password = (EditText)findViewById(R.id.input_password);
        input_comfirm_password = (EditText)findViewById(R.id.input_comfirm_password);

        btn_signup = (Button)findViewById(R.id.btn_signup);
        lay_group_password = (LinearLayout)findViewById(R.id.lay_group_password);

        btn_signup.setOnClickListener(this);

        if(str_id.length()>0){
            input_first_name.setText(str_first_name);
            input_last_name.setText(str_last_name);
            input_email.setText(str_email);
            lay_group_password.setVisibility(View.GONE);
            btn_signup.setText(getString(R.string.txt_confirm));
        }

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_signup:
                setRegister();
                break;
        }

    }

    public void setRegister(){
        try {
            boolean cancel = false;
            View focusView = null;

            str_first_name  = input_first_name.getText().toString().trim();
            str_last_name  = input_last_name.getText().toString().trim();
            str_email  = input_email.getText().toString().trim();
            str_phone  = input_phone.getText().toString().trim();
            str_password  = input_password.getText().toString().trim();
            str_comfirm_password = input_comfirm_password.getText().toString().trim();

            if(str_id.length()==0){
                if(str_first_name.length()==0){
                    input_first_name.setError(getString(R.string.error_invalid_first_name));
                    focusView = input_first_name;
                    cancel = true;
                }else if(str_last_name.length()==0){
                    input_last_name.setError(getString(R.string.error_invalid_last_name));
                    focusView = input_last_name;
                    cancel = true;
                }else if(!isEmailValid(str_email)){
                    input_email.setError(getString(R.string.error_invalid_email));
                    focusView = input_email;
                    cancel = true;
                }else if(!isPhoneNumberValid(str_phone)){
                    input_phone.setError(getString(R.string.error_invalid_phone));
                    focusView = input_phone;
                    cancel = true;
                }else if(!isPassWordValid(str_password)){
                    input_password.setError(getString(R.string.error_invalid_pass));
                    focusView = input_password;
                    cancel = true;
                }else if(!isConfirmPassWordValid(str_password,str_comfirm_password)){
                    input_comfirm_password.setError(getString(R.string.error_invalid_pass));
                    focusView = input_comfirm_password;
                    cancel = true;
                }
            }else {
                if(str_first_name.length()==0){
                    input_first_name.setError(getString(R.string.error_invalid_first_name));
                    focusView = input_first_name;
                    cancel = true;
                }else if(str_last_name.length()==0){
                    input_last_name.setError(getString(R.string.error_invalid_last_name));
                    focusView = input_last_name;
                    cancel = true;
                }else if(!isEmailValid(str_email)){
                    input_email.setError(getString(R.string.error_invalid_email));
                    focusView = input_email;
                    cancel = true;
                }else if(!isPhoneNumberValid(str_phone)){
                    input_phone.setError(getString(R.string.error_invalid_phone));
                    focusView = input_phone;
                    cancel = true;
                }
            }

            if(cancel)
                focusView.requestFocus();
            else{
                str_authen = "user";
                if(str_id.length()>0){
                    str_status = "1";
                }else {
                    str_id = "0";
                    str_status = "0";
                }
                mApiService = new ApiService();
                mSignUpPresenter = new SignUpPresenter(this,mApiService);
                mSignUpPresenter.loadRegister();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPhoneNumberValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length()==10;
    }

    private boolean isPassWordValid(String pass) {
        //TODO: Replace this with your own logic
        return (pass.length()>7&&pass.length()<16);
    }

    private boolean isConfirmPassWordValid(String pass , String confirm) {
        //TODO: Replace this with your own logic
        return (pass.equals(confirm));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AccessToken.setCurrentAccessToken(null);
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
