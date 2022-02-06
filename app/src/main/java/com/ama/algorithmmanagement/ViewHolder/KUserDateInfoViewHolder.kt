package com.ama.algorithmmanagement.ViewHolder;

import android.graphics.Color
import android.view.ViewGroup
import com.ama.algorithmmanagement.Base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemUserDateInfoBinding
import com.ama.algorithmmanagement.model.DateInfo
import com.ama.algorithmmanagement.model.DateObject
import com.ama.algorithmmanagement.model.YearInfo
import com.ama.algorithmmanagement.utils.ColorUtils
import com.ama.algorithmmanagement.utils.DateUtils
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2021-12-31
 * class : KUserDateInfoViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class KUserDateInfoViewHolder(private val parent: ViewGroup) :
    KBaseViewHolder<ItemUserDateInfoBinding>(
        parent, R.layout.item_user_date_info
    ) {
    fun setData(dateInfo: DateInfo) {
        val dateValue = ColorUtils.dateInfoConvert(dateInfo.count)
        binding.content.setBackgroundColor(dateValue)
    }
}