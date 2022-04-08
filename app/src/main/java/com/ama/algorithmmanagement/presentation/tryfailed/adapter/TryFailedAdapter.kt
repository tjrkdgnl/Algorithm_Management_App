package com.ama.algorithmmanagement.presentation.tryfailed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ama.algorithmmanagement.common.viewholder.ProblemViewHolder
import com.ama.algorithmmanagement.domain.entity.TaggedProblem

class TryFailedAdapter(var listClickListener: (TaggedProblem) -> Unit) : RecyclerView.Adapter<ProblemViewHolder>() {
    private val list = mutableListOf<TaggedProblem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, listClickListener)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setData(list[position], null)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<TaggedProblem>) {
        for (i in list.indices) {
            // check contains list item object
            if ( list[i] in this.list )
                continue
            this.list.add(list[i])
        }

        notifyDataSetChanged()
    }
}