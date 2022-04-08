package com.ama.algorithmmanagement.presentation.main.view_holder;

import android.content.res.ColorStateList
import android.view.ViewGroup
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemUserDateInfoBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.domain.entity.DateInfo
import com.ama.algorithmmanagement.utils.ColorUtils
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
    fun setData(dateTriple: Triple<DateInfo,Int,Boolean>) {
        binding.apply {
            Timber.e("$dateTriple")
            // 날짜표시
            date = dateTriple.second.toString()
            // 현재달의 날짜인지 여부 , 이전달의 날짜 다음달의 날짜의경우 글자 흐려짐
            isCurrentDate = dateTriple.third
            // 유저의 활동개수에 따라 색상 다르게
            content.backgroundTintList = ColorStateList.valueOf(ColorUtils.dateInfoConvert(dateTriple.first.count))
        }
       // binding.content.backgroundTintList = ColorStateList.valueOf(parent.context.resources.getColor(R.color.barchart_color))
//        binding.content.setBackgroundColor(dateValue)
    }
}