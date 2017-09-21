package com.oit.test.retrofit_post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("/addUser")
    Call<Post> savePost (@Body Post model);
}