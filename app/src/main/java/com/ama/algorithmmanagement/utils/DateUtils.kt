package com.ama.algorithmmanagement.utils

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

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

    fun getStatDate() = statsDate

    // 현재 월의 첫 시작일(n월 1일)의 위치(요일) 리턴
    fun getStatDateStartDay():Int{
        val tempDay = statsDate.get(Calendar.DAY_OF_MONTH)
        statsDate.set(Calendar.DAY_OF_MONTH,1)
        val startDay = statsDate.get(Calendar.DAY_OF_WEEK)
        statsDate.set(Calendar.DAY_OF_MONTH,tempDay)
        return startDay
    }
    // 현재 월의 최대 일(n월의 끝일 ex) 3월의 경우 31일이 끝일이기때문에 31 리턴)
    fun getStatDateMaxDay():Int = statsDate.getActualMaximum(Calendar.DAY_OF_MONTH)

    //
    fun getDateMaxDay(cal:Calendar):Int{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR,cal.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH,cal.get(Calendar.MONTH))
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
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