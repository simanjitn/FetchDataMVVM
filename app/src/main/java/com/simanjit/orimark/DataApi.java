package com.simanjit.orimark;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataApi {
    @GET("todos/{ID}")
    Call<UserResponse> getUserDetail(@Path("ID") Integer Id);
}
