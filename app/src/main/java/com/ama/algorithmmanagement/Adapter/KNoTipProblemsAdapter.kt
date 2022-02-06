package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.model.TipProblemInfo

class KNoTipProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val list = mutableListOf<TaggedProblem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<TipProblemInfo>?) {
        for (i in list?.indices!!) {
            this.list.add(list[i].problem!!)
        }
        notifyDataSetChanged()

    }


}