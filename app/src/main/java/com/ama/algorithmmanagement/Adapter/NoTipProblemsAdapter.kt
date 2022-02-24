package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.model.TipProblemInfo

class NoTipProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val noTipList = mutableListOf<TaggedProblem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, 0)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setData(noTipList[position], false)
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