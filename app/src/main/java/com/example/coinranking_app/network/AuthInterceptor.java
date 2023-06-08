package com.example.coinranking_app.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String HOST_HEADER_NAME = "x-rapidapi-host";
    private static final String HOST_HEADER_VALUE = "coinranking1.p.rapidapi.com";
    private static final String KEY_HEADER_NAME = "x-rapidapi-key";
    private static final String API_KEY = "0ff3130a89msh8ce0c357b2f69f9p11f4bbjsnf6a87a57962a";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = new Request.Builder(chain.request())
                .addHeader(HOST_HEADER_NAME, HOST_HEADER_VALUE)
                .addHeader(KEY_HEADER_NAME, API_KEY)
                .build();

        return chain.proceed(request);
    }
}
