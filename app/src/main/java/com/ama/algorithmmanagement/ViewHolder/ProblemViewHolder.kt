package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.Model.Problem
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding

class ProblemViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent, R.layout.default_solved_problems_item
    ) {

    fun setData(data: Problem) {
        binding.title.text = data.titleKo
        binding.problemId.text = data.problemId.toString()
    }

}