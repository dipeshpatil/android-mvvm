package io.github.dipeshpatil.androidmvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.R;
import io.github.dipeshpatil.androidmvvm.model.PhotoModel;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {

    private final Context context;
    private List<PhotoModel> photoModelList;

    public PhotoListAdapter(Context context, List<PhotoModel> photoModelList) {
        this.context = context;
        this.photoModelList = photoModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPhotoModelList(List<PhotoModel> photoModelList) {
        this.photoModelList = photoModelList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_photo_row, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoModel photoModel = this.photoModelList.get(position);

        holder.titleTextView.setText(photoModel.getTitle());
        Picasso.get().load(photoModel.getUrl()).into(holder.photoUrlImageView);
    }

    @Override
    public int getItemCount() {
        if (this.photoModelList != null)
            return this.photoModelList.size();
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView photoUrlImageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.photo_title_text_view);
            photoUrlImageView = itemView.findViewById(R.id.photo_url_image_view);
        }
    }
}
