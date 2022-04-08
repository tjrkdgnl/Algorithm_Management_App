package com.ama.algorithmmanagement.presentation.notip.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ama.algorithmmanagement.common.viewholder.ProblemViewHolder
import com.ama.algorithmmanagement.domain.entity.TaggedProblem
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo

class NoTipProblemsAdapter(var listClickListener: (TaggedProblem) -> Unit) : RecyclerView.Adapter<ProblemViewHolder>() {
    private val noTipList = mutableListOf<TaggedProblem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, listClickListener)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setData(noTipList[position], null)
    }

    override fun getItemCount(): Int {
        return noTipList.size
    }

    fun updateList(list: MutableList<TipProblemInfo>) {
        for (i in list.indices) {
            list[i].problem?.let { this.noTipList.add(it) }
        }
        notifyDataSetChanged()

    }
}