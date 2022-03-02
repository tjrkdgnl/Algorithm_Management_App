package com.ama.algorithmmanagement.viewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultCommentItemBinding
import com.ama.algorithmmanagement.model.CommentInfo

class TestCommentViewHolder(parent: ViewGroup, private val moveToChild: (CommentInfo) -> Unit) :
    KBaseViewHolder<DefaultCommentItemBinding>(parent, R.layout.default_comment_item) {
    private lateinit var commentInfo: CommentInfo

    init {
        binding.root.setOnClickListener {
            moveToChild(commentInfo)
        }
    }

    fun setCommentInfo(info: CommentInfo) {
        commentInfo = info
    }

    fun bind(comment: String) {
        binding.comment = comment
        binding.executePendingBindings()
    }

}