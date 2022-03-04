package com.ama.algorithmmanagement.viewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultSearchItemBinding
import com.ama.algorithmmanagement.data.model.Keyword

class TestSearchViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultSearchItemBinding>(parent, R.layout.default_search_item) {

    fun setData(keyword: Keyword) {
        binding.keyword = keyword
        binding.executePendingBindings()
    }

}