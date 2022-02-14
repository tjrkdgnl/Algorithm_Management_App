package com.ama.algorithmmanagement.Adapter;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.TipProblemViewHolder
import com.ama.algorithmmanagement.model.TipProblemInfo
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : TipProblemViewPagerAdpater.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class TipProblemViewPagerAdapter(private val skipPage:()-> Unit) : RecyclerView.Adapter<TipProblemViewHolder>(){
    private val mList = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):TipProblemViewHolder {
        return TipProblemViewHolder(parent,skipPage)
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
    fun addData(data:TipProblemInfo){
        Timber.e("add data")
        mList.add(data)
    }
}