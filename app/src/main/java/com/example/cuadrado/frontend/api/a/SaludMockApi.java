package com.example.cuadrado.frontend.api.a;

import com.example.cuadrado.frontend.api.a.model.Affiliate;
import com.example.cuadrado.frontend.api.a.model.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by cuadrado on 08/11/2017.
 */

public interface SaludMockApi {
    // TODO: Cambiar host por "10.0.3.2" para Genymotion.
    // TODO: Cambiar host por "10.0.2.2" para AVD.
    // TODO: Cambiar host por IP de tu PC para dispositivo real.
    public static final String BASE_URL = "http://192.168.100.4/v1/";

    @POST("affiliates/login")
    Call<Affiliate> login(@Body LoginBody loginBody);
}
