package com.ama.algorithmmanagement.presentation.mytip.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.common.viewholder.ProblemViewHolder
import com.ama.algorithmmanagement.domain.entity.TaggedProblem
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo

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