package com.may.maystream.shopngai.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.may.maystream.shopngai.R;
import com.may.maystream.shopngai.activity.AddOrderActivity;
import com.may.maystream.shopngai.activity.MainActivity;

/**
 * Created by may on 8/18/2017.
 */

public class SellFragment extends Fragment {

    public static SellFragment newInstance(){
        SellFragment fragment = new SellFragment();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sell, container, false);
        View view = inflater.inflate (R.layout.bottom_sheet, null);
        TextView txt_select_from_gallery = (TextView)view.findViewById( R.id.txt_select_from_gallery);
        TextView txt_cancel = (TextView)view.findViewById( R.id.txt_cancel);
        final Dialog mBottomSheetDialog = new Dialog (getActivity(),
                R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.setCancelable (true);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mBottomSheetDialog.show ();
        txt_select_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddOrderActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
                mBottomSheetDialog.dismiss();
            }
        });
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
                mBottomSheetDialog.dismiss();
            }
        });
        return rootView;
    }

}
