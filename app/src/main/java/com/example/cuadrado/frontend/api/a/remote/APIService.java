package com.example.cuadrado.frontend.api.a.remote;

import com.example.cuadrado.frontend.api.a.model.Post;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by cuadrado on 15/11/2017.
 */

public interface APIService {
    @POST("doc")
    @FormUrlEncoded
    Call<Post> savePost(@Field("id") String id,
                              @Field("hash_password") String hash_password,
                              @Field("name") String name,
                              @Field("address") String address,
                              @Field("gender") String gender,
                              @Field("token") String token,
                              @Field("url") String url);
}
