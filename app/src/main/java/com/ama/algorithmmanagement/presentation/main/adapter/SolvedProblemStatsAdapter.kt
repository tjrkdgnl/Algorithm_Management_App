package com.ama.algorithmmanagement.presentation.main.adapter;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.presentation.main.view_holder.SolvedProblemStatsViewHolder

/**
 * @author SeungHo Lee
 * summary :
 */
class SolvedProblemStatsAdapter :RecyclerView.Adapter<SolvedProblemStatsViewHolder>(){
    private val mList= mutableListOf<Pair<String,Int>>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SolvedProblemStatsViewHolder {
        return SolvedProblemStatsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SolvedProblemStatsViewHolder, position: Int) {
        holder.setData(mList[position].second,mList[position].first)
    }

    override fun getItemCount(): Int= mList.size

    fun addItem(data:Pair<String,Int>){
        mList.add(data)
        notifyItemInserted(mList.size-1)
    }
}