package io.github.dipeshpatil.androidmvvm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.R;
import io.github.dipeshpatil.androidmvvm.adapter.TodoListAdapter;
import io.github.dipeshpatil.androidmvvm.model.TodoModel;
import io.github.dipeshpatil.androidmvvm.viewmodel.TodoListViewModel;

public class TodosFragment extends Fragment {
    private List<TodoModel> todoModelList;
    private TodoListAdapter todoListAdapter;

    public TodosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_todos, container, false);

        RecyclerView todosFragmentRecyclerView = mView.findViewById(R.id.recycler_view_todos_fragment);

        todoListAdapter = new TodoListAdapter(mView.getContext(), todoModelList);
        todosFragmentRecyclerView.setAdapter(todoListAdapter);

        TodoListViewModel todoListViewModel = ViewModelProviders.of(this).get(TodoListViewModel.class);
        todoListViewModel.getTodoListObserver().observe(getViewLifecycleOwner(), todoModels -> {
            if (todoModels != null) {
                todoModelList = todoModels;
                todoListAdapter.setTodoModelList(todoModelList);
            }
        });
        todoListViewModel.makeAPICall();

        return mView;
    }
}