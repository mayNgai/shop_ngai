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

/**
 * Created by May on 8/18/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input_first_name,input_last_name,input_email,input_phone,input_password,input_comfirm_password;
    private Button btn_signup;
    private LinearLayout lay_group_password;
    private String str_id = "",str_first_name  = "",str_last_name  = "",str_email  = "",str_phone  = "",str_password  = "",str_comfirm_password  = "";

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
            btn_signup.setText(getString(R.string.txt_comfirm));
        }

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_signup:
                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(i);
                finish();

                break;
        }

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
