package io.github.dipeshpatil.androidmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.PostModel;
import io.github.dipeshpatil.androidmvvm.network.APIService;
import io.github.dipeshpatil.androidmvvm.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListViewModel extends ViewModel {
    private final MutableLiveData<List<PostModel>> postList;

    public PostListViewModel() {
        postList = new MutableLiveData<>();
    }

    public MutableLiveData<List<PostModel>> getPostListObserver() {
        return postList;
    }

    public void makeAPICall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<PostModel>> call = apiService.getPostList();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postList.postValue(response.body());
                Log.d("PostListViewModel:ResponseBody", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("PostListViewModel:Message", t.getMessage());
                Log.d("PostListViewModel:Cause", String.valueOf(t.getCause()));
                Log.d("PostListViewModel:StackTrace", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
