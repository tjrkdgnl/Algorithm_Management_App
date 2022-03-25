package com.ama.algorithmmanagement.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.presentation.main.view_holder.KRetryProblemViewHolder
import com.ama.algorithmmanagement.data.model.TipProblemInfo

class KRetryProblemsAdapter :RecyclerView.Adapter<KRetryProblemViewHolder>(){
    private val mList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KRetryProblemViewHolder {
        return KRetryProblemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KRetryProblemViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int =mList.size

    fun setRetryProblemItem(list:List<TipProblemInfo>?){
        mList.clear()
        list?.let {
            mList.addAll(it)
        }
        notifyDataSetChanged()
    }
}