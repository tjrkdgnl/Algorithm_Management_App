package com.ama.algorithmmanagement.ViewHolder.test

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultIdeaItemBinding

class IdeaViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultIdeaItemBinding>(parent, R.layout.default_idea_item) {

    fun setData(idea: String?) {
        binding.idea = idea
    }
}