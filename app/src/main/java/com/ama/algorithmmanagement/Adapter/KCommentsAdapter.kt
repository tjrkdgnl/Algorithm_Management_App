package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Activity.AdapterListener
import com.ama.algorithmmanagement.ViewHolder.CommentViewHolder
import com.ama.algorithmmanagement.model.CommentInfo
/**
 * author : manyong Han
 * summary : 댓글 어댑터
 */
class KCommentsAdapter(
    var listener: AdapterListener
) : RecyclerView.Adapter<CommentViewHolder>() {
    private val list = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.setData(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun updateList(list: MutableList<CommentInfo>?) {
        this.list.clear()
        list?.let {
            this.list.addAll(it)
            notifyItemRangeChanged(0, this.list.size)
        }

    }
}