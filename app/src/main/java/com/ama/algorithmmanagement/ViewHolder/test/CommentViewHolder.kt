package com.ama.algorithmmanagement.ViewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultCommentItemBinding

class CommentViewHolder(parent: ViewGroup, private val moveToChild: (String) -> Unit) :
    KBaseViewHolder<DefaultCommentItemBinding>(parent, R.layout.default_comment_item) {
    private var commentId: String? = null

    init {
        binding.root.setOnClickListener {
            commentId?.let(moveToChild)
        }
    }

    fun setCommentId(commentId: String) {
        this.commentId = commentId
    }

    fun bind(comment: String) {
        binding.comment = comment
        binding.executePendingBindings()
    }

}