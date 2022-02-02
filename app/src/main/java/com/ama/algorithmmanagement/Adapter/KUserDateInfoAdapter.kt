package com.ama.algorithmmanagement.Adapter;

import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.KUserDateInfoViewHolder
import com.ama.algorithmmanagement.databinding.ItemUserDateInfoBinding
import com.ama.algorithmmanagement.model.DateInfo

/**
 * @author  seungHo
 * @since  2021-12-31
 * class : KUserDateAdapter.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : {@link KMainActivity.kt} 에서 나의 실력그래프에서 날짜별 통계 리사이클러뷰 어댑터
 */
class KUserDateInfoAdapter:RecyclerView.Adapter<KUserDateInfoViewHolder>(){
    private val mList:MutableList<DateInfo> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KUserDateInfoViewHolder {
        return KUserDateInfoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KUserDateInfoViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    fun setList(list:MutableList<DateInfo>?){
        list?.let{
            mList.addAll(it)
        }
    }

}