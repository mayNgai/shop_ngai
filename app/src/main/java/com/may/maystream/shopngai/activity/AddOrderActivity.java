package com.may.maystream.shopngai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.may.maystream.shopngai.R;

/**
 * Created by may on 9/6/2017.
 */

public class AddOrderActivity extends AppCompatActivity{
    private Spinner spn_category,spn_condition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spn_category = (Spinner)findViewById(R.id.spn_category);
        spn_condition = (Spinner)findViewById(R.id.spn_condition);
        setCategory();
        setCondition();
    }
    private void setCategory(){
        String[] category = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, category);
        spn_category.setAdapter(adapterCategory);
    }

    private void setCondition(){
        String[] condition = getResources().getStringArray(R.array.condition);
        ArrayAdapter<String> adapterCondition = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, condition);
        spn_condition.setAdapter(adapterCondition);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_order, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                this.finish();
                return true;
            case R.id.action_submit:
//                myAccountPresenter.updateProfile();
                Toast.makeText(this, "Submit", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }
}
