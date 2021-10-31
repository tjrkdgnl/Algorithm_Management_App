package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Model.KProblemsOfClass
import com.ama.algorithmmanagement.ViewHolder.KDefaultViewHolder

class KDefaultRecyclerViewAdapter : RecyclerView.Adapter<KDefaultViewHolder>() {
    private val list = mutableListOf<KProblemsOfClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KDefaultViewHolder {
        return KDefaultViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KDefaultViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<KProblemsOfClass>?) {
        list?.let {
            this.list.addAll(it)
            notifyDataSetChanged()
        }
    }

}