package com.ama.algorithmmanagement.presentation.vpdetail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.domain.entity.ChildCommentInfo
import com.ama.algorithmmanagement.presentation.vpdetail.view_holder.ChildCommentViewHolder

/**
 * author : manyong Han
 * summary : 대댓글 어댑터
 */
class KChildCommentsAdapter() : RecyclerView.Adapter<ChildCommentViewHolder>() {
    private val list = mutableListOf<ChildCommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildCommentViewHolder {
        return ChildCommentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ChildCommentViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<ChildCommentInfo>?) {
        this.list.clear() // 리스트 중복으로 쌓이는 현상 발생 방지
        list?.let {
            this.list.addAll(it)

            notifyDataSetChanged()
        }
    }
}