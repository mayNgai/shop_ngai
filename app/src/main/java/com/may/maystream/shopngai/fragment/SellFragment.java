package com.may.maystream.shopngai.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
    static final Integer READ_EXST = 0x4;
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
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
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

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            }
        } else {
//            Toast.makeText(getActivity(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

}
