package com.simanjit.orimark;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private MutableLiveData<UserResponse> userResponseMutableLiveData;

    public LiveData<UserResponse> getUserResponse(Integer test) {

            userResponseMutableLiveData = new MutableLiveData<UserResponse>();
            loadUserDetail(test);

        return userResponseMutableLiveData;
    }


    //This method is using Retrofit to get the JSON data from URL
    public void loadUserDetail(Integer test) {

        DataApi dataApi = APIService.getClient().create(DataApi.class);

        dataApi.getUserDetail(test).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, final Response<UserResponse> response) {

                userResponseMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("STATUS", "ResponseRequestFailed " + t);
            }
        });

    }

}
