package com.may.maystream.shopngai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.may.maystream.shopngai.R;

/**
 * Created by may on 8/18/2017.
 */

public class SellingFragment extends Fragment {

    public static  SellingFragment newInstance(){
        SellingFragment fragment = new SellingFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selling, container, false);


        return rootView;
    }
}
