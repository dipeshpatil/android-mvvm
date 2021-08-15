package io.github.dipeshpatil.androidmvvm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.R;
import io.github.dipeshpatil.androidmvvm.adapter.PhotoListAdapter;
import io.github.dipeshpatil.androidmvvm.model.PhotoModel;
import io.github.dipeshpatil.androidmvvm.viewmodel.PhotoListViewModel;

public class PhotosFragment extends Fragment {
    private List<PhotoModel> photoModelList;
    private PhotoListAdapter photoListAdapter;

    public PhotosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_photos, container, false);

        RecyclerView photosFragmentRecyclerView = mView.findViewById(R.id.recycler_view_photos_fragment);

        photoListAdapter = new PhotoListAdapter(mView.getContext(), photoModelList);
        photosFragmentRecyclerView.setLayoutManager(new GridLayoutManager(mView.getContext(), 2));
        photosFragmentRecyclerView.setAdapter(photoListAdapter);

        PhotoListViewModel photoListViewModel = ViewModelProviders.of(this).get(PhotoListViewModel.class);
        photoListViewModel.getPhotoListObserver().observe(getViewLifecycleOwner(), photoModels -> {
            if (photoModels != null) {
                photoModelList = photoModels;
                photoListAdapter.setPhotoModelList(photoModelList);
            }
        });
        photoListViewModel.makeAPICall();

        return mView;
    }
}
