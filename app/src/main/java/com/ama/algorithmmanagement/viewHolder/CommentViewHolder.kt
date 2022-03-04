package com.ama.algorithmmanagement.viewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.data.model.CommentInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.CommentViewItemBinding
/**
 * author : manyong Han
 * summary : 댓글 뷰홀더
 */

class CommentViewHolder(private val parent: ViewGroup, childClickListener: (CommentInfo) -> Unit) :
    KBaseViewHolder<CommentViewItemBinding>(
        parent, R.layout.comment_view_item
    ) {
    private lateinit var commentInfo: CommentInfo

    init {
        binding.commentChildCommentCount.setOnClickListener {
            childClickListener(commentInfo)
        }
    }

    fun setData(data: CommentInfo) {
        commentInfo = data

        binding.commentTitle.text = data.userId
        binding.commentContent.text = data.comment
        binding.commentDate.text = data.date
        binding.commentChildCommentCount.text = String.format("답글 %s", data.commentChildCount)

        binding.executePendingBindings()
    }
}