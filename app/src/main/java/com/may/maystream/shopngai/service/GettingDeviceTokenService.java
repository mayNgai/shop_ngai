package com.may.maystream.shopngai.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by May on 9/12/2017.
 */

public class GettingDeviceTokenService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        String DeviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("DeviceToken ==> ",  DeviceToken);
    }
}
