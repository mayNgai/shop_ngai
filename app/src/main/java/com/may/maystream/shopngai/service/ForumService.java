package com.may.maystream.shopngai.service;

import com.may.maystream.shopngai.model.TblCategory;
import com.may.maystream.shopngai.model.TblMember;
import com.may.maystream.shopngai.model.TblMyItem;
import com.may.maystream.shopngai.model.Tbldiscount;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by May on 8/23/2017.
 */

public class ForumService {
    public static final String FORUM_SERVER_URL = "http://10.255.248.63:80/shop_ngai";
    private ForumApi mForumApi;


    public ForumService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORUM_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mForumApi = restAdapter.create(ForumApi.class);
    }


    public ForumApi getApi() {

        return mForumApi;
    }

    public interface ForumApi {


        @POST("/category.php")
        public Observable<List<TblCategory>> getCategory();

        @POST("/discount.php")
        public Observable<List<Tbldiscount>> getDiscount();

        @FormUrlEncoded
        @POST("/myItem.php")
        public Observable<List<TblMyItem>> getMyItem(@Field("o_vendor_id") int o_vendor_id);

        @FormUrlEncoded
        @POST("/getItemByType.php")
        public Observable<List<TblMyItem>> getItemByType(@Field("o_c_id") int o_c_id);

        @POST("/register.php")
        public Observable<List<TblCategory>> createMember();

        @FormUrlEncoded
        @POST("/loginAuthen.php")
        public Observable<TblMember> getLogin(@Field("email") String email, @Field("password") String password);
    }
}
