package io.github.dipeshpatil.androidmvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.adapter.PostListAdapter;
import io.github.dipeshpatil.androidmvvm.model.PostModel;
import io.github.dipeshpatil.androidmvvm.viewmodel.PostListViewModel;


public class PostsFragment extends Fragment {
    private List<PostModel> postModelList;
    private PostListAdapter postListAdapter;

    public PostsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView postsFragmentRecyclerView = mView.findViewById(R.id.recycler_view_posts_fragment);

        postListAdapter = new PostListAdapter(mView.getContext(), postModelList);
        postsFragmentRecyclerView.setAdapter(postListAdapter);

        PostListViewModel postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        postListViewModel.getPostListObserver().observe(getViewLifecycleOwner(), postModels -> {
            if (postModels != null) {
                postModelList = postModels;
                postListAdapter.setPostModelList(postModelList);
            }
        });
        postListViewModel.makeAPICall();

        return mView;
    }
}