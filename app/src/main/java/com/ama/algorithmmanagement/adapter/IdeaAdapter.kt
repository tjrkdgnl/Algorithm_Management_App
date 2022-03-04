package com.ama.algorithmmanagement.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.IdeaViewHolder
import com.ama.algorithmmanagement.data.model.IdeaInfo

class IdeaAdapter : RecyclerView.Adapter<IdeaViewHolder>() {
    private val lst = mutableListOf<IdeaInfo?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        return IdeaViewHolder(parent)
    }

    override fun onBindViewHolder(holderTest: IdeaViewHolder, position: Int) {
        holderTest.setData(lst[position]?.comment, lst[position]?.date, lst[position]?.url)
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