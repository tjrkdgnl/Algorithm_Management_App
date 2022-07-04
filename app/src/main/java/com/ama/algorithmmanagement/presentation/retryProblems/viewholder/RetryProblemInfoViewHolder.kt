package com.ama.algorithmmanagement.presentation.retryProblems.viewholder;

import android.view.ViewGroup
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemRetryProblemBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.utils.TipUtils

/**
 * @author SeungHo Lee
 * summary :
 */
class RetryProblemInfoViewHolder(parent: ViewGroup) :
    KBaseViewHolder<ItemRetryProblemBinding>(parent, R.layout.item_retry_problem) {
        fun setData(data:TipProblemInfo){
            binding.isSolved = data.tipComment != null
            binding.tvTitle.text = data.problem?.problemId.toString()
            binding.tvProblemTitle.text = data.problem?.titleKo
            binding.tvLevel.text = "Lv. ${data.problem?.level.toString()}"
//            binding.tvDescription.text = TipUtils.tagsConvertToString(data.problem?.tags)
            binding.tvDescription.text = data.problem?.problemId.toString()
            binding.executePendingBindings()

        }
}