package com.ama.algorithmmanagement.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.Model.TipProblem
import com.ama.algorithmmanagement.ViewHolder.ProblemViewHolder

class KNoTipProblemsAdapter : RecyclerView.Adapter<ProblemViewHolder>() {
    private val list = mutableListOf<TaggedProblem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<TipProblem>?) {
        for (i in list?.indices!!) {
            this.list.add(list[i].problem)
        }
        notifyDataSetChanged()

        // todo - 리시버 블록 활용해서 it 처리를 어떻게 tagged problem을 가지고오지..?
//        list?.let {
//            this.list.addAll(it.problem)
//            notifyDataSetChanged()
//        }
    }


}