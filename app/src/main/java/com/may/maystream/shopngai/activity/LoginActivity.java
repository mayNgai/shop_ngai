package com.may.maystream.shopngai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.presenters.HomePresenter;
import com.may.maystream.shopngai.presenters.LoginPresenter;
import com.may.maystream.shopngai.service.ForumService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by may on 8/18/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    public EditText input_email,input_password;
    private Button btn_login;
    private TextView txt_forgot,txt_signup;

    private ForumService mForumService;
    private LoginPresenter mLoginPresenter;

    public static String strEmail , strPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton)findViewById(R.id.login_button);
        input_email = (EditText)findViewById(R.id.input_email);
        input_password = (EditText)findViewById(R.id.input_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        txt_forgot = (TextView)findViewById(R.id.txt_forgot);
        txt_signup = (TextView)findViewById(R.id.txt_signup);

        btn_login.setOnClickListener(this);
        txt_signup.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "user_birthday"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.i("LoginActivity", response.toString());
                            Bundle bFacebookData = getFacebookData(object);
                            String id = object.getString("id");
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String gender = object.getString("gender");
                            String birthday = object.getString("birthday");
                            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                            intent.putExtra("id",id);
                            intent.putExtra("first_name",first_name);
                            intent.putExtra("last_name",last_name);
                            intent.putExtra("email",email);
                            startActivity(intent);

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            Log.d("TAG","Error parsing JSON");
        }
        return null;
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_login:
                strEmail = input_email.getText().toString();
                strPassword = input_password.getText().toString();
                mForumService = new ForumService();
                mLoginPresenter = new LoginPresenter(this, mForumService);
                mLoginPresenter.loadLogin();
                break;
            case R.id.txt_signup:
                Intent a = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(a);

                break;
        }

    }

    public void updateLogin(TblMember member){
        try {
            if(member.getSuccess().equalsIgnoreCase("0")){
                Toast.makeText(getApplicationContext(), member.getMessage(), Toast.LENGTH_SHORT).show();
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
                t.setSuccess(member.getSuccess());
                t.setMessage(member.getMessage());
                t.setId(member.getId());
                TaskController taskController = new TaskController();
                taskController.createMember(t);
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
