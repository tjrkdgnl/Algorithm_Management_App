package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo

class MyTipProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val myTipList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent, 2)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        myTipList[position].problem?.let { holder.setData(it, myTipList[position].isShow) }
    }

    override fun getItemCount(): Int {
        return myTipList.size
    }

    fun updateList(list: MutableList<TipProblemInfo>) {
        myTipList.addAll(list)
        notifyDataSetChanged()
    }
}