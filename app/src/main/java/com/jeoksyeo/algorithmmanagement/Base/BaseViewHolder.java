package com.jeoksyeo.algorithmmanagement.Base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected T binding;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutRes) {
        super(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),layoutRes,parent,false).getRoot());
        binding = DataBindingUtil.bind(this.itemView);
    }
}
