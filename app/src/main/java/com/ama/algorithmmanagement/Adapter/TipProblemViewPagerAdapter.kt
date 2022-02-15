package com.ama.algorithmmanagement.Adapter;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.TipProblemViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : TipProblemViewPagerAdpater.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description  뷰페이져2 리사이클려뷰 어댑터
 */
class TipProblemViewPagerAdapter(private val viewModel:NewSolvedProblemViewModel) : RecyclerView.Adapter<TipProblemViewHolder>(){
    private val mList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):TipProblemViewHolder {
        return TipProblemViewHolder(parent,viewModel)
    }

    override fun onBindViewHolder(holder: TipProblemViewHolder, position: Int) {
        Timber.e("bind viewholder")
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int =mList.size

    fun setData(data:MutableList<TipProblemInfo>){
        mList.addAll(data)
        notifyDataSetChanged()
    }
}