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
import io.github.dipeshpatil.androidmvvm.model.TodoModel;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {

    private final Context context;
    private List<TodoModel> todoModelList;

    public TodoListAdapter(Context context, List<TodoModel> todoModelList) {
        this.context = context;
        this.todoModelList = todoModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTodoModelList(List<TodoModel> todoModelList) {
        this.todoModelList = todoModelList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_todo_row, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        TodoModel todoModel = this.todoModelList.get(position);

        if (todoModel.getCompleted())
            holder.titleTextView.setTextColor(context.getResources().getColor(R.color.success));
        else
            holder.titleTextView.setTextColor(context.getResources().getColor(R.color.error));

        holder.titleTextView.setText(todoModel.getTitle());
    }

    @Override
    public int getItemCount() {
        if (this.todoModelList != null)
            return this.todoModelList.size();
        return 0;
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.todo_title_text_view);
        }
    }
}
