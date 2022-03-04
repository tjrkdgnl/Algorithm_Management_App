package com.ama.algorithmmanagement.viewHolder

import android.view.View
import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding
import com.ama.algorithmmanagement.data.model.TaggedProblem
import com.ama.algorithmmanagement.data.model.TipProblemInfo
import com.google.android.material.chip.Chip

class ProblemViewHolder(private val parent: ViewGroup, listClickListener: (TaggedProblem) -> Unit) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent,
        R.layout.default_solved_problems_item
    ) {

    private lateinit var mClickProblem: TaggedProblem

    init {
        itemView.setOnClickListener {
            listClickListener(mClickProblem)
        }
    }

    fun setData(data: TaggedProblem, tipProblemInfo: TipProblemInfo?) {
        binding.title.text = data.titleKo
        binding.problemId.text = data.problemId.toString()

        // set member variable
        mClickProblem = data

        if (tipProblemInfo != null) {
            if (tipProblemInfo.isShow)
                binding.check.visibility = View.VISIBLE
            else
                binding.check.visibility = View.GONE
        } else {
            binding.check.visibility = View.GONE
        }

        val tagList = data.tags
        // Observing 될 때 마다 구성 완료된 칩 그룹에 add view 할 필요가 없어서 분기 하였다.
        if (tagList.size != binding.chipGroupTags.childCount) {
            for (i in tagList.indices) {
                val chip = Chip(parent.context)
                chip.text = tagList[i].displayNames[0].name
                binding.chipGroupTags.addView(chip)
            }
        }
    }
}