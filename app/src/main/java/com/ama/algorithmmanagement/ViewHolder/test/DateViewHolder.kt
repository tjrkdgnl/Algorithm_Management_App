package com.ama.algorithmmanagement.ViewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultDateItemBinding
import com.ama.algorithmmanagement.databinding.DefaultIdeaItemBinding

class DateViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultDateItemBinding>(parent, R.layout.default_date_item) {

    fun setData(date: String?) {
        binding.date = date
    }
}