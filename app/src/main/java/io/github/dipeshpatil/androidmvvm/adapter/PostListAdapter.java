package io.github.dipeshpatil.androidmvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.dipeshpatil.androidmvvm.R;
import io.github.dipeshpatil.androidmvvm.helpers.Utils;
import io.github.dipeshpatil.androidmvvm.model.PostModel;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    private final Context context;
    private List<PostModel> postModelList;

    public PostListAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPostModelList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_posts_row, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel postModel = this.postModelList.get(position);

        holder.titleTextView.setText(Utils.convertToTitleCase(postModel.getTitle()));
        holder.bodyTextView.setText(postModel.getBody());
    }

    @Override
    public int getItemCount() {
        if (this.postModelList != null)
            return this.postModelList.size();
        return 0;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView bodyTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.post_title_text_view);
            bodyTextView = itemView.findViewById(R.id.post_body_text_view);
        }
    }
}
