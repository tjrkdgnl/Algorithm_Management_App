package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.IdeaInfo
import com.ama.algorithmmanagement.ViewHolder.test.IdeaViewHolder

class IdeaAdpater : RecyclerView.Adapter<IdeaViewHolder>() {
    private val lst = mutableListOf<IdeaInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        return IdeaViewHolder(parent)
    }

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        holder.setData(lst[position].comment)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(mutableList: MutableList<IdeaInfo>) {
        for (ideaInfo in mutableList) {
            if (!lst.contains(ideaInfo)) {
                lst.add(ideaInfo)
            }
        }
        notifyDataSetChanged()
    }
}