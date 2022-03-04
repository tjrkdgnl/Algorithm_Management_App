package com.ama.algorithmmanagement.domain.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class KBaseViewHolder<T : ViewDataBinding>(parent: ViewGroup, @LayoutRes layoutRes: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    protected val binding: T by lazy {
        DataBindingUtil.bind(itemView)!!
    }


}