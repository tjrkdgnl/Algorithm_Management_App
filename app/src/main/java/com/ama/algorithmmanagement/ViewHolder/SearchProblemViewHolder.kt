package com.ama.algorithmmanagement.ViewHolder;

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemSearchProblemBinding
import com.ama.algorithmmanagement.model.Keyword

/**
 * @author : seungHo
 * @since : 2022-02-14
 * class : SearchProblemViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 검색 결과 리사이클러뷰 뷰홀더
 */
class SearchProblemViewHolder(parent:ViewGroup) :KBaseViewHolder<ItemSearchProblemBinding>(parent,
    R.layout.item_search_problem){
    // TODO : 파이어베이스에서 해당 문제를 풀었다면 체크표시
    fun setData(data:Keyword){
        binding.keyword = data
        binding.executePendingBindings()
    }
}