package com.may.maystream.shopngai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.may.maystream.shopngai.R;

/**
 * Created by may on 8/18/2017.
 */

public class SellingFragment extends Fragment {
    private Spinner spn_type_item,spn_type_of_ship;

    public static  SellingFragment newInstance(){
        SellingFragment fragment = new SellingFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selling, container, false);
        spn_type_item = (Spinner)rootView.findViewById(R.id.spn_type_item);
        spn_type_of_ship = (Spinner)rootView.findViewById(R.id.spn_type_of_ship);
        setTypeOfItem();
        setTypeOfShip();
        return rootView;
    }

    private void setTypeOfItem(){
        String[] typeOfItem = getResources().getStringArray(R.array.typeofItem);
        ArrayAdapter<String> adapterTypeOfItem = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, typeOfItem);
        spn_type_item.setAdapter(adapterTypeOfItem);
    }

    private void setTypeOfShip(){
        String[] typeOfShip = getResources().getStringArray(R.array.typeofship);
        ArrayAdapter<String> adapterTypeOfShip = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, typeOfShip);
        spn_type_of_ship.setAdapter(adapterTypeOfShip);
    }
}
