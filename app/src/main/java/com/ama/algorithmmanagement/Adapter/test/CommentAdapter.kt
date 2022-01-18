package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Model.CommentInfo
import com.ama.algorithmmanagement.ViewHolder.test.CommentViewHolder

class CommentAdapter(private val moveToChild: (String) -> Unit) :
    RecyclerView.Adapter<CommentViewHolder>() {
    private val lst = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent, moveToChild)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(lst[position].comment)
        holder.setCommentId(lst[position].commentId)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(commentList: MutableList<CommentInfo>?) {
        commentList?.let {
            lst.addAll(it)
        }
        notifyDataSetChanged()
    }
}