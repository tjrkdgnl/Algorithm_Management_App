package com.ama.algorithmmanagement.viewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultIdeaItemBinding

class TestIdeaViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultIdeaItemBinding>(parent, R.layout.default_idea_item) {

    fun setData(idea: String?) {
        binding.idea = idea
    }
}