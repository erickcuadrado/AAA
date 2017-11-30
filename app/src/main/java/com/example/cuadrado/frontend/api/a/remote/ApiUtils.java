package com.example.cuadrado.frontend.api.a.remote;

/**
 * Created by cuadrado on 15/11/2017.
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.100.4/extras/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
