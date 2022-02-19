package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo

class MyTipProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val list = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, 2)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        list[position].problem?.let { holder.setData(it, list[position].isShow) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<TipProblemInfo>?) {
        list?.let { this.list.addAll(it) }
        notifyDataSetChanged()
    }

}