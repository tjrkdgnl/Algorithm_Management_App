package com.ama.algorithmmanagement.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtils {
    private val date: String by lazy {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")
        val date = Date()
        dateFormat.format(date)
    }


    fun createDate(): String {
        return date
    }

}