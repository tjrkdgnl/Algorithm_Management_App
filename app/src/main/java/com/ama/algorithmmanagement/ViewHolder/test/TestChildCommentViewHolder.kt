package com.ama.algorithmmanagement.ViewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultChildcommentItemBinding

class TestChildCommentViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultChildcommentItemBinding>(
        parent,
        R.layout.default_childcomment_item
    ) {

    fun bind(comment: String) {
        binding.comment = comment
        binding.executePendingBindings()
    }
}