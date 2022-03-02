package com.ama.algorithmmanagement.viewHolder

import android.view.ViewGroup
import com.ama.algorithmmanagement.base.KBaseViewHolder
import com.ama.algorithmmanagement.model.KProblemsOfClass
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.DefaultViewholderItemBinding

class KDefaultViewHolder(parent: ViewGroup) :
    KBaseViewHolder<DefaultViewholderItemBinding>(parent, R.layout.default_viewholder_item) {

    fun setData(data: KProblemsOfClass) {
        binding.textviewDeafultItem.text = data._class.toString()
        binding.executePendingBindings()
    }
}