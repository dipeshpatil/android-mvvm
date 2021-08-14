package io.github.dipeshpatil.androidmvvm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.adapter.PostListAdapter;
import io.github.dipeshpatil.androidmvvm.model.PostModel;
import io.github.dipeshpatil.androidmvvm.viewmodel.PostListViewModel;

public class MainActivity extends AppCompatActivity {

    private List<PostModel> postModelList;
    private PostListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        movieListAdapter = new PostListAdapter(this, postModelList);
        recyclerView.setAdapter(movieListAdapter);

        PostListViewModel postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        postListViewModel.getMoviesListObserver().observe(this, movieModels -> {
            if (movieModels != null) {
                postModelList = movieModels;
                movieListAdapter.setMoviesList(postModelList);
            }
        });
        postListViewModel.makeAPICall();
    }
}
