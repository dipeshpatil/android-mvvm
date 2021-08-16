package io.github.dipeshpatil.androidmvvm.network;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.PhotoModel;
import io.github.dipeshpatil.androidmvvm.model.PostModel;
import io.github.dipeshpatil.androidmvvm.model.TodoModel;
import io.github.dipeshpatil.androidmvvm.model.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/posts")
    Call<List<PostModel>> getPostList();

    @GET("/todos")
    Call<List<TodoModel>> getTodoList();

    @GET("/photos")
    Call<List<PhotoModel>> getPhotoList();

    @GET("/users")
    Call<List<UserModel>> getUserList();
}
