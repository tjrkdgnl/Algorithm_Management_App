package com.ama.algorithmmanagement.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.model.TaggedProblem

class KSolvedProblemsAdapter(var listClickListener: (TaggedProblem) -> Unit) : RecyclerView.Adapter<ProblemViewHolder>() {
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


    fun updateList(list: MutableList<TaggedProblem>?) {
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }

    }

}