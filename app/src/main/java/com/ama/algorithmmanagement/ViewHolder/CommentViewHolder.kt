package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.CommentListItemBinding
import com.ama.algorithmmanagement.model.CommentInfo
import timber.log.Timber

class CommentViewHolder(parent: ViewGroup) : KBaseViewHolder<CommentListItemBinding>(parent, R.layout.comment_list_item) {

    fun setData(data: CommentInfo) {
        Timber.d(data.toString())
        binding.title.text = data.comment
        binding.date.text = data.date
        binding.childCommentCount.text = data.commentChildCount.toString()
        binding.executePendingBindings()
    }
}