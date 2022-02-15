package com.ama.algorithmmanagement.Adapter;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.ViewHolder.SearchProblemViewHolder
import com.ama.algorithmmanagement.model.Keyword

/**
 * @author : seungHo
 * @since : 2022-02-14
 * class : SearchPorblemAdapter.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 백준 문제 검색 결과 API 보여줄 리사이클러뷰 어댑터
 */
class SearchProblemAdapter :RecyclerView.Adapter<SearchProblemViewHolder>(){
    private val mList = mutableListOf<Keyword>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProblemViewHolder {
        return SearchProblemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchProblemViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int =mList.size

    fun cleanData(){
        if(mList.size!=0){
            mList.clear()
            notifyDataSetChanged()
        }
    }
    fun setData(data:MutableList<Keyword>?){
        data?.let{
            mList.addAll(data)
            notifyDataSetChanged()
        }
    }
}