package com.ama.algorithmmanagement.presentation.tryhistory.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.domain.entity.IdeaInfo
import com.ama.algorithmmanagement.presentation.tryhistory.view_holder.IdeaViewHolder

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