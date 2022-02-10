package com.ama.algorithmmanagement.ViewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultIdeaItemBinding
import com.ama.algorithmmanagement.databinding.IdeaListItemBinding

class IdeaViewHolder(parent: ViewGroup) :
    KBaseViewHolder<IdeaListItemBinding>(parent, R.layout.idea_list_item) {

    fun setData(idea: String?, date:String?) {
        binding.idea = idea
        binding.date = date
    }
}