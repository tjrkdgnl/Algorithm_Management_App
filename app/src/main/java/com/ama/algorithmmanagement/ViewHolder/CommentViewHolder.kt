package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.CommentListItemBinding
import com.ama.algorithmmanagement.model.CommentInfo

class CommentViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<CommentListItemBinding>(
        parent,
        R.layout.comment_list_item
    ) {

    fun setData(data: CommentInfo) {
        binding.title.text = data.comment
        binding.date.text = data.date
    }

}