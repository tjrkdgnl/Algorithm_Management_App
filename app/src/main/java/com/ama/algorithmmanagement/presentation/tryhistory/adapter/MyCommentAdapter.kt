package com.ama.algorithmmanagement.presentation.tryhistory.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.presentation.tryhistory.view_holder.MyCommentViewHolder
import com.ama.algorithmmanagement.data.model.CommentInfo

/**
 * author : hongdroid94
 * summary : 내가 작성한 댓글
 */
class MyCommentAdapter(
    var childClickListener: (CommentInfo) -> Unit
) : RecyclerView.Adapter<MyCommentViewHolder>() {
    private val list = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentViewHolder {
        return MyCommentViewHolder(parent, childClickListener)
    }

    override fun onBindViewHolder(holder: MyCommentViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(commentList: MutableList<CommentInfo>) {
        list.addAll(commentList)
        notifyDataSetChanged()
    }
}
