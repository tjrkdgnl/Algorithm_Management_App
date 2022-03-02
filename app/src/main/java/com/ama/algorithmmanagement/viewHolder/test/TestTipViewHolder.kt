package com.ama.algorithmmanagement.viewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.base.KBaseViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding

class TestTipViewHolder(val parent: ViewGroup, private val moveToWriteAct: (TipProblemInfo) -> Unit) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent,
        R.layout.default_solved_problems_item
    ) {

    private var taggedProblem: TipProblemInfo? = null

    init {
        binding.root.setOnClickListener {
            taggedProblem?.let(moveToWriteAct)
        }
    }

    fun bind(info: TipProblemInfo) {
        info.problem?.let {
            binding.problem = it
        }

        this.taggedProblem = info
    }
}