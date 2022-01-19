package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder

class KSolvedProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val list = mutableListOf<TipProblemInfo>()

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
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }

    }

}