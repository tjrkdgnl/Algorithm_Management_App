package com.ama.algorithmmanagement.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtils {
    private lateinit var date: String
    private lateinit var dateArray: IntArray


    init {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")
        val dateObj = Date()
        date = dateFormat.format(dateObj)
        dateArray = date.split(".").map { it.toInt() }.toIntArray()
    }

    fun getDate(): String {
        return date
    }

    fun getYear(): Int {
        return dateArray[0]
    }

    fun getMonth(): Int {
        return dateArray[1]
    }

    fun getDay(): Int {
        return dateArray[2]
    }

}