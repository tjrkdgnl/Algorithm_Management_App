package com.ama.algorithmmanagement.viewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemRetryProblemBinding
import com.ama.algorithmmanagement.model.TipProblemInfo

class KRetryProblemViewHolder(parent: ViewGroup) :
    KBaseViewHolder<ItemRetryProblemBinding>(
        parent, R.layout.item_retry_problem
    ) {
    fun setData(problem:TipProblemInfo) {
        binding.tvTitle.text = problem.problem?.titleKo
        binding.tvDescription.text = problem.tipComment
        binding.tvLevel.text = problem.problem?.level.toString()
        binding.executePendingBindings()
    }
}