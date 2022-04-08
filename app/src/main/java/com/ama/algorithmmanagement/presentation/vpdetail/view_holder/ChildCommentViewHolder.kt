package com.ama.algorithmmanagement.presentation.vpdetail.view_holder

import android.view.ViewGroup
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.CommentChildViewItemBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.domain.entity.ChildCommentInfo

/**
 * author : manyong Han
 * summary : 대댓글 뷰홀더
 */

class ChildCommentViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<CommentChildViewItemBinding>(
        parent, R.layout.comment_child_view_item
    ) {

    fun setData(data: ChildCommentInfo) {
        binding.commentTitle.text = data.userId
        binding.commentContent.text = data.comment
        binding.commentDate.text = data.date

        binding.executePendingBindings()
    }
}