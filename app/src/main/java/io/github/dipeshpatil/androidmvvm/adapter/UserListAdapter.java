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
import io.github.dipeshpatil.androidmvvm.model.UserModel;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final Context context;
    private List<UserModel> userModelList;

    public UserListAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUserModelList(List<UserModel> userModelList) {
        this.userModelList = userModelList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_user_row, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel userModel = this.userModelList.get(position);

        holder.nameTextView.setText("Name: " + userModel.getName());
        holder.emailTextView.setText("Email: " + userModel.getEmail());
        holder.usernameTextView.setText("Username: " + userModel.getUsername());
        holder.phoneTextView.setText("Phone: " + userModel.getPhone());
        holder.websiteTextView.setText("Website: " + userModel.getWebsite());
        holder.companyTextView.setText("Company: " + userModel.getCompany().getName());
    }

    @Override
    public int getItemCount() {
        if (this.userModelList != null)
            return this.userModelList.size();
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView emailTextView;
        TextView usernameTextView;
        TextView phoneTextView;
        TextView websiteTextView;
        TextView companyTextView;

        public UserViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.user_name_text_view);
            emailTextView = itemView.findViewById(R.id.user_email_text_view);
            usernameTextView = itemView.findViewById(R.id.user_username_text_view);
            phoneTextView = itemView.findViewById(R.id.user_phone_text_view);
            websiteTextView = itemView.findViewById(R.id.user_website_text_view);
            companyTextView = itemView.findViewById(R.id.user_company_text_view);
        }
    }
}
