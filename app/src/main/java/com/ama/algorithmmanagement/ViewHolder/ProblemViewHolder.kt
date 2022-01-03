package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding
import com.google.android.material.chip.Chip

class ProblemViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent, R.layout.default_solved_problems_item
    ) {

    fun setData(data: TaggedProblem) {
        binding.title.text = data.titleKo
        binding.problemId.text = data.problemId.toString()

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