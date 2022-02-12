package com.ama.algorithmmanagement.ViewHolder

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.ama.algorithmmanagement.Activity.kDefault.TryHistoryActivity
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSolvedProblemsItemBinding
import com.ama.algorithmmanagement.model.TaggedProblem
import com.google.android.material.chip.Chip

class ProblemViewHolder(private val parent: ViewGroup, private val clickType: Int) :
    KBaseViewHolder<DefaultSolvedProblemsItemBinding>(
        parent,
        R.layout.default_solved_problems_item
    ) {

    // 동일 뷰홀더를 공유하는 화면들이 있기에 클릭 방식을 분기하기 위해 임시로 상수 구현
    private val CLICK_TYPE_NOT_TIPPING = 0
    private val CLICK_TYPE_TRY_FAILED  = 1
    private val CLICK_TYPE_MY_TIPPING  = 2

    fun setData(data: TaggedProblem, isShow: Boolean) {
        binding.title.text = data.titleKo
        binding.problemId.text = data.problemId.toString()

        if (clickType == CLICK_TYPE_MY_TIPPING) {
            if (isShow)
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

        itemView.setOnClickListener {

            when (clickType) {
                CLICK_TYPE_NOT_TIPPING -> {
                    // 팁을 작성하지 않은 문제

                    // todo - 팁 작성 화면으로 이동
//                    val intent = Intent(parent.context, ::class.java)
//                    intent.putExtra("problemId", data.problemId)
//                    parent.context.startActivity(intent)
                }

                CLICK_TYPE_TRY_FAILED  -> {
                    // 시도했으나 실패한 문제

                    val strOptionArry = arrayOf("문제보기 (코멘트 작성화면)", "문제 풀이 히스토리")
                    val builder = AlertDialog.Builder(parent.context)
                    builder.setItems(strOptionArry) { _, position ->
                        when (position) {
                            0-> {
                                // 문제보기 (코멘트 작성화면)

                            }
                            1-> {
                                // 문제 풀이 히스토리
                                val intent = Intent(parent.context, TryHistoryActivity::class.java)
                                intent.putExtra("problemId", data.problemId)
                                parent.context.startActivity(intent)
                            }
                        }
                    }
                    builder.show()
                }

                CLICK_TYPE_MY_TIPPING  -> {
                    // 내가 작성한 팁
                }
            }

        }
    }
}