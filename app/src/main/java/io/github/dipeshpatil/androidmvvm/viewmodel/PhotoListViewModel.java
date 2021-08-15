package io.github.dipeshpatil.androidmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.PhotoModel;
import io.github.dipeshpatil.androidmvvm.network.APIService;
import io.github.dipeshpatil.androidmvvm.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListViewModel extends ViewModel {
    private final MutableLiveData<List<PhotoModel>> photoList;

    public PhotoListViewModel() {
        photoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<PhotoModel>> getPhotoListObserver() {
        return photoList;
    }

    public void makeAPICall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<PhotoModel>> call = apiService.getPhotoList();
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                photoList.postValue(response.body());
                Log.d("PhotoListViewModel:ResponseBody", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Log.d("PhotoListViewModel:Message", t.getMessage());
                Log.d("PhotoListViewModel:Cause", String.valueOf(t.getCause()));
                Log.d("PhotoListViewModel:StackTrace", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
