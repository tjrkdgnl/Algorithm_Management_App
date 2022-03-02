package com.ama.algorithmmanagement.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.CommentViewHolder
import com.ama.algorithmmanagement.model.CommentInfo
/**
 * author : manyong Han
 * summary : 댓글 어댑터
 */
class KCommentsAdapter(
    var childClickListener: (CommentInfo) -> Unit
) : RecyclerView.Adapter<CommentViewHolder>() {
    private val list = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent, childClickListener)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun updateList(list: MutableList<CommentInfo>?) {
        this.list.clear() // 리스트 중복으로 쌓이는 현상 발생 방지
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }
    }
}