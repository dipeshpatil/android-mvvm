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
import io.github.dipeshpatil.androidmvvm.adapter.UserListAdapter;
import io.github.dipeshpatil.androidmvvm.model.UserModel;
import io.github.dipeshpatil.androidmvvm.viewmodel.UserListViewModel;

public class UsersFragment extends Fragment {
    private List<UserModel> userModelList;
    private UserListAdapter userListAdapter;

    public UsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_users, container, false);

        RecyclerView usersFragmentRecyclerView = mView.findViewById(R.id.recycler_view_users_fragment);

        userListAdapter = new UserListAdapter(mView.getContext(), userModelList);
        usersFragmentRecyclerView.setAdapter(userListAdapter);

        UserListViewModel userListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        userListViewModel.getUserListObserver().observe(getViewLifecycleOwner(), userModels -> {
            if (userModels != null) {
                userModelList = userModels;
                userListAdapter.setUserModelList(userModelList);
            }
        });
        userListViewModel.makeAPICall();

        return mView;
    }
}