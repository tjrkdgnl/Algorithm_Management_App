package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Activity.AdapterListener
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.CommentViewItemBinding
/**
 * author : manyong Han
 * summary : 댓글 뷰홀더
 */
class CommentViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<CommentViewItemBinding>(
        parent, R.layout.comment_view_item
    ) {

    fun setData(data: CommentInfo, listener: AdapterListener) {
        binding.commentTitle.text = data.userId
        binding.commentContent.text = data.comment
        binding.commentDate.text = data.date
        binding.commentChildCommentCount.text = String.format("답글 %s", data.commentChildCount)

        binding.executePendingBindings()

        binding.commentChildCommentCount.setOnClickListener {
            listener.adapterClickListener(data)
        }
    }

}