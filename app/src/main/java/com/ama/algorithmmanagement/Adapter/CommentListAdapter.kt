package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.CommentViewHolder
import com.ama.algorithmmanagement.model.CommentInfo

/**
 * author : hongdroid94
 */
class CommentListAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val list = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(commentList: MutableList<CommentInfo>?) {
        commentList?.let {
            list.addAll(it)
        }
        notifyDataSetChanged()
    }
}
