package com.ama.algorithmmanagement.presentation.main.view_holder;

import android.view.ViewGroup
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemProblemStatsProgressBarBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder

/**
 * @author SeungHo Lee
 * summary :
 */
class SolvedProblemStatsViewHolder(parent: ViewGroup) :
    KBaseViewHolder<ItemProblemStatsProgressBarBinding>(
        parent,
        R.layout.item_problem_stats_progress_bar
    ) {
        fun setData(progress:Int,title:String){
            binding.pgItemProgressProblem.progress = progress
            binding.tvProgressTitle.text = title
            binding.tvSolvedCount.text = "$progress 개 해결"
        }
}