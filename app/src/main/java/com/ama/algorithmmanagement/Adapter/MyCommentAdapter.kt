package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.CommentViewHolder
import com.ama.algorithmmanagement.ViewHolder.MyCommentViewHolder
import com.ama.algorithmmanagement.model.CommentInfo

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
