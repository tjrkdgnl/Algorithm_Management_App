package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.ChildCommentInfo
import com.ama.algorithmmanagement.ViewHolder.test.ChildCommentViewHolder

class TestChildCommentAdapter :
    RecyclerView.Adapter<ChildCommentViewHolder>() {

    private val lst = mutableListOf<ChildCommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildCommentViewHolder {
        return ChildCommentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ChildCommentViewHolder, position: Int) {
        holder.bind(lst[position].comment)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(childCommentList: MutableList<ChildCommentInfo>?) {
        childCommentList?.let {
            lst.addAll(it)
        }
        notifyDataSetChanged()
    }
}