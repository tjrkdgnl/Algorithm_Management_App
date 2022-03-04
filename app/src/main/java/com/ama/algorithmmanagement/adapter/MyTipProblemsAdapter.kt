package com.ama.algorithmmanagement.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.data.model.TaggedProblem
import com.ama.algorithmmanagement.data.model.TipProblemInfo

class MyTipProblemsAdapter(var listClickListener: (TaggedProblem) -> Unit) : RecyclerView.Adapter<ProblemViewHolder>() {
    private val myTipList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, listClickListener)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        myTipList[position].problem?.let {
            holder.setData(it, myTipList[position])
        }
    }

    override fun getItemCount(): Int {
        return myTipList.size
    }

    fun updateList(list: MutableList<TipProblemInfo>) {
        myTipList.addAll(list)
        notifyDataSetChanged()
    }
}