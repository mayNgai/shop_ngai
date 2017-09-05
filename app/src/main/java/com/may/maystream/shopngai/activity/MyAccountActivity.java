package com.may.maystream.shopngai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.controller.TaskController;
import com.may.maystream.shopngai.model.TblMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may on 9/5/2017.
 */

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input_first_name,input_last_name,input_tel,input_mail;
    private ImageView imgEditFirstName,imgEditLastName,imgEditTel,imgEditEmail;
    private Button btnLogOut;
    private TaskController taskController;
    private List<TblMember> tblMembers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        taskController = new TaskController();
        init();
        setDefault();
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
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        imgEditFirstName.setOnClickListener(this);
        imgEditLastName.setOnClickListener(this);
        imgEditTel.setOnClickListener(this);
        imgEditEmail.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    private void setText(){
        tblMembers = new ArrayList<TblMember>();
        tblMembers = taskController.checkMember();
        input_first_name.setText(tblMembers.get(0).getFirst_name());
        input_last_name.setText(tblMembers.get(0).getLast_name());
        input_tel.setText(tblMembers.get(0).getTel());
        input_mail.setText(tblMembers.get(0).getEmail());
    }

    private void setDefault(){
        input_first_name.setEnabled(false);
        input_last_name.setEnabled(false);
        input_tel.setEnabled(false);
        input_mail.setEnabled(false);
        imgEditFirstName.setImageResource(R.drawable.ic_mode_edit_black_24dp);
        imgEditLastName.setImageResource(R.drawable.ic_mode_edit_black_24dp);
        imgEditTel.setImageResource(R.drawable.ic_mode_edit_black_24dp);
        imgEditEmail.setImageResource(R.drawable.ic_mode_edit_black_24dp);
        setText();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgEditFirstName:
                break;
            case R.id.imgEditLastName:
                break;
            case R.id.imgEditTel:
                break;
            case R.id.imgEditEmail:
                break;
            case R.id.btnLogOut:
                break;
            default:
                break;
        }
    }

    public void updateProfile(TblMember member){
        try {
            if(member.getSuccess().equalsIgnoreCase("0")){
                Toast.makeText(getApplicationContext(), member.getMessage(), Toast.LENGTH_SHORT).show();
            }else if(member.getSuccess().equalsIgnoreCase("1")){
                tblMembers.get(0).setAuthentication(member.getAuthentication());
                tblMembers.get(0).setDate_register(member.getDate_register());
                tblMembers.get(0).setEmail(member.getEmail());
                tblMembers.get(0).setFirst_name(member.getFirst_name());
                tblMembers.get(0).setLast_name(member.getLast_name());
                tblMembers.get(0).setStatus(member.getStatus());
                tblMembers.get(0).setTel(member.getTel());
                tblMembers.get(0).setUser_id(member.getUser_id());
                tblMembers.get(0).setPassword("");
                tblMembers.get(0).setSuccess(member.getSuccess());
                tblMembers.get(0).setMessage(member.getMessage());
                tblMembers.get(0).setId(member.getId());
                taskController.updateMember(tblMembers.get(0));
                setDefault();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
