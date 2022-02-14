package com.ama.algorithmmanagement.ViewHolder;

import android.view.View
import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.FragmentNewSolvedProblemBinding
import com.ama.algorithmmanagement.model.TipProblemInfo

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : TipProblemViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class TipProblemViewHolder(private val view: ViewGroup, private val skipPage:()-> Unit):KBaseViewHolder<FragmentNewSolvedProblemBinding>(
    view,R.layout.fragment_new_solved_problem
) {
    fun setData(data:TipProblemInfo){
        binding.data = data
        binding.skipPage.setOnClickListener{
            skipPage()
        }

    }
}