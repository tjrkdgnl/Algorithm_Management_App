package com.ama.algorithmmanagement.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.ViewHolder.KRetryProblemViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo

class KRetryProblemsAdapter :RecyclerView.Adapter<KRetryProblemViewHolder>(){
    private val mList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KRetryProblemViewHolder {
        return KRetryProblemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KRetryProblemViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int =mList.size

    fun setRetryProblemItem(list:MutableList<TipProblemInfo>?){
        list?.let {
            mList.addAll(it)
        }
        notifyDataSetChanged()
    }
}