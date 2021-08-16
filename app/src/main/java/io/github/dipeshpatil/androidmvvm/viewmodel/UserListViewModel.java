package io.github.dipeshpatil.androidmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.UserModel;
import io.github.dipeshpatil.androidmvvm.network.APIService;
import io.github.dipeshpatil.androidmvvm.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends ViewModel {
    private final MutableLiveData<List<UserModel>> userList;

    public UserListViewModel() {
        userList = new MutableLiveData<>();
    }

    public MutableLiveData<List<UserModel>> getUserListObserver() {
        return userList;
    }

    public void makeAPICall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<UserModel>> call = apiService.getUserList();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                userList.postValue(response.body());
                Log.d("UserListViewModel:ResponseBody", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.d("UserListViewModel:Message", t.getMessage());
                Log.d("UserListViewModel:Cause", String.valueOf(t.getCause()));
                Log.d("UserListViewModel:StackTrace", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
