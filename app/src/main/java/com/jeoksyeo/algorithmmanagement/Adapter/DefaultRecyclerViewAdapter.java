package com.jeoksyeo.algorithmmanagement.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeoksyeo.algorithmmanagement.Model.ProblemsOfClass;
import com.jeoksyeo.algorithmmanagement.ViewHolder.DefaultViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DefaultRecyclerViewAdapter extends RecyclerView.Adapter<DefaultViewHolder> {
    private List<ProblemsOfClass> list;

    public DefaultRecyclerViewAdapter() {
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DefaultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefaultViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<ProblemsOfClass> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
