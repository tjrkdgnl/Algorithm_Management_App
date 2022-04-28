package com.ama.algorithmmanagement.presentation.retryProblems.adapter;

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.presentation.retryProblems.viewholder.RetryProblemInfoViewHolder
import timber.log.Timber


/**
 * @author SeungHo Lee
 * summary :
 */
class RetryProblemsInfoAdapter: RecyclerView.Adapter<RetryProblemInfoViewHolder>() {
    private val mList = mutableListOf<TipProblemInfo>()
    private lateinit var listener:OnRetryProblemClickListener
    interface OnRetryProblemClickListener{
        fun onClick(v: View,data:TipProblemInfo)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetryProblemInfoViewHolder {
        return RetryProblemInfoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RetryProblemInfoViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            this.listener.onClick(it,mList[position])
        }
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int=mList.size

    fun setOnItemClickListener(listener:OnRetryProblemClickListener){
        this.listener = listener
    }

    fun setData(data:MutableList<TipProblemInfo>){
        mList.addAll(data)
        notifyDataSetChanged()
    }
    fun clearData(){
        mList.clear()
    }
}