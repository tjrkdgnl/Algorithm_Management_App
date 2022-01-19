package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.ViewHolder.test.TipViewHolder

class TestTipAdapter(
    private val moveToWriteTip: (TipProblemInfo) -> Unit
) : RecyclerView.Adapter<TipViewHolder>() {

    private val lst = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        return TipViewHolder(parent, moveToWriteTip)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(lst[position])
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(problems: List<TipProblemInfo>?) {
        problems?.let {
            lst.addAll(it)
            notifyDataSetChanged()
        }
    }
}