package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding

class ProblemViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent, R.layout.default_solved_problems_item
    ) {

    fun setData(data: TipProblemInfo) {
        binding.title.text = data.problem?.titleKo

    }

}