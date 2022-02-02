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

    private val statsDate by lazy{
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")
        val currentDate = dateFormat.parse(date)
        val calendar:Calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar
    }
    fun nextMonth(){
        statsDate.set(Calendar.MONTH, statsDate.get(Calendar.MONTH)+1)
    }
    fun prevMonth(){
        statsDate.set(Calendar.MONTH, statsDate.get(Calendar.MONTH)-1)
    }

    fun getCalendarDate():String = "${statsDate.get(Calendar.YEAR)}.${statsDate.get(Calendar.MONTH)+1}.${statsDate.get(Calendar.DAY_OF_MONTH)}"

    fun getCalendarYearMonth():String = "${statsDate.get(Calendar.YEAR)}년 ${statsDate.get(Calendar.MONTH)+1}월"

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