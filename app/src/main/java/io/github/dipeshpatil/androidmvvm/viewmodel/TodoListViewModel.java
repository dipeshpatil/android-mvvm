package io.github.dipeshpatil.androidmvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import io.github.dipeshpatil.androidmvvm.model.TodoModel;
import io.github.dipeshpatil.androidmvvm.network.APIService;
import io.github.dipeshpatil.androidmvvm.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoListViewModel extends ViewModel {
    private final MutableLiveData<List<TodoModel>> todoList;

    public TodoListViewModel() {
        todoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TodoModel>> getTodoListObserver() {
        return todoList;
    }

    public void makeAPICall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<TodoModel>> call = apiService.getTodoList();
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                todoList.postValue(response.body());
                Log.d("TodoListViewModel:ResponseBody", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                Log.d("TodoListViewModel:Message", t.getMessage());
                Log.d("TodoListViewModel:Cause", String.valueOf(t.getCause()));
                Log.d("TodoListViewModel:StackTrace", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}
