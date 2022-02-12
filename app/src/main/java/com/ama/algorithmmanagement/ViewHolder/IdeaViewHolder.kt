package com.ama.algorithmmanagement.ViewHolder

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultIdeaItemBinding
import com.ama.algorithmmanagement.databinding.IdeaListItemBinding

class IdeaViewHolder(parent: ViewGroup) :
    KBaseViewHolder<IdeaListItemBinding>(parent, R.layout.idea_list_item) {

    fun setData(idea: String?, date:String?, uri:String?) {
        binding.idea = idea
        binding.date = date
        if (!TextUtils.isEmpty(uri)) {
            binding.ivIdea.visibility = View.VISIBLE
            binding.ivIdea.setImageURI(uri?.toUri())
        } else {
            binding.ivIdea.visibility = View.GONE
        }
    }
}