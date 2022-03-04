package com.ama.algorithmmanagement.viewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultDateItemBinding

class TestDateViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultDateItemBinding>(parent, R.layout.default_date_item) {

    fun setData(date: String?) {
        binding.date = date
    }
}