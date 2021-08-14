package io.github.dipeshpatil.androidmvvm.network;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.PostModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/posts")
    Call<List<PostModel>> getPostList();
}
