package com.reynaldiwijaya.smartrt.Api;

import com.reynaldiwijaya.smartrt.Helper.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientNews {
    private static Retrofit retrofit = null;

    public static Retrofit getClientNews() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.INDONESIA_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
