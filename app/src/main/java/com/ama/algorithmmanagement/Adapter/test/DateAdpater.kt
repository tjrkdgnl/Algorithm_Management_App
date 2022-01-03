package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Model.DateInfo
import com.ama.algorithmmanagement.Model.IdeaInfo
import com.ama.algorithmmanagement.ViewHolder.test.DateViewHolder
import com.ama.algorithmmanagement.ViewHolder.test.IdeaViewHolder

class DateAdpater : RecyclerView.Adapter<DateViewHolder>() {
    private val lst = mutableListOf<DateInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.setData(lst[position].date)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(mutableList: MutableList<DateInfo>) {
        for (dateInfo in mutableList) {
            if (!lst.contains(dateInfo)) {
                lst.add(dateInfo)
            }
        }
        notifyDataSetChanged()
    }
}