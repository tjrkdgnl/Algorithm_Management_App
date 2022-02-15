package com.ama.algorithmmanagement.ViewHolder;

import android.view.View
import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.FragmentNewSolvedProblemBinding
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : TipProblemViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 팁 작성화면 뷰홀더
 */
class TipProblemViewHolder(private val view: ViewGroup, private val viewModel:NewSolvedProblemViewModel):KBaseViewHolder<FragmentNewSolvedProblemBinding>(
    view,R.layout.fragment_new_solved_problem
) {
    fun setData(data:TipProblemInfo){
        binding.data = data
        binding.viewmodel = viewModel

    }
}