package com.ama.algorithmmanagement.presentation.main.adapter;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.domain.entity.DateInfo
import com.ama.algorithmmanagement.presentation.main.view_holder.KUserDateInfoViewHolder
import com.ama.algorithmmanagement.utils.DateUtils
import java.util.*

/**
 * @author  seungHo
 * @since  2021-12-31
 * class : KUserDateAdapter.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : MainActivity 에서 나의 실력그래프에서 날짜별 통계 리사이클러뷰 어댑터
 */
class KUserDateInfoAdapter:RecyclerView.Adapter<KUserDateInfoViewHolder>(){
    // Triple first :DateInfo 객체, second :  날짜, third 날짜가 현재 월의 날짜인지여부
    private val mList:MutableList<Triple<DateInfo,Int,Boolean>> = mutableListOf()
    private lateinit var currentDate:Calendar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KUserDateInfoViewHolder {
        return KUserDateInfoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: KUserDateInfoViewHolder, position: Int) {
        holder.setData(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    /**
     * 월별 통계에서 보려는 날짜가 바뀔때마다 그래프를 다시 그려야되기때문에 리스트 비우고 초기화
     */
    fun initialize(){
        mList.clear()
        notifyDataSetChanged()
    }

    /**
     * @param date 보여줄 월별통계 날짜
     * @param dateInfoData 월별 통계 데이터
     * 해당월의 월별 통계데이터를 어댑터에 추가
     */
    fun setList(date: Calendar,dateInfoData:HashMap<Int,Int>?=null){
        currentDate = date
        fetchList(dateInfoData)
        notifyDataSetChanged()
    }
    private fun fetchList(data:HashMap<Int,Int>?){
        val startDay = DateUtils.getStatDateStartDay()-1
        // n 월 1일 전까지는 빈칸으로 채움 ex) 1월 1일이 금요일일경우 첫주 월,화,수,목 은 이전달의 날짜로 채움
        val previousMonthCalendar = Calendar.getInstance()
        previousMonthCalendar.set(Calendar.YEAR,currentDate.get(Calendar.YEAR))
        previousMonthCalendar.set(Calendar.MONTH,currentDate.get(Calendar.MONTH)-1)
        val previousMonthMaxDay = DateUtils.getDateMaxDay(previousMonthCalendar)
        for(i in 0 until startDay){
            mList.add(Triple(DateInfo("",-1),previousMonthMaxDay-(startDay-i)+1,false))
        }
        // n 월의 max 일은 우선 기본값(count=0) 으로 채움 ex) 1월 의 경우 31일이기떄문에 기본값으로 31칸 채움
        for(i in 0 until DateUtils.getStatDateMaxDay()){
            val makeDate = "${currentDate.get(Calendar.YEAR)}.${currentDate.get(Calendar.MONTH)+1}.${String.format("%02d",i)}"
            mList.add(Triple(DateInfo(makeDate,0),i+1,true))
        }
        // 달력 리사이클러뷰 채우고 남는칸 다음 월날짜로 채움
        if(mList.size%7!=0){
            for(i in 0 until 7-(mList.size%7)){
                mList.add(Triple(DateInfo("",-1),i+1,false))
            }
        }

        // 유저 활동기록이 있는 일은 그데이터로 교채 ex) 1월 3일에 count = 3 일경우 해당 리스트값을 변경
        data?.map{
            // 57 번줄 로직에서 빈칸채울때 들어가는 수치 추가해서 지정
            val tempDate = mList[it.key-1+startDay].first.date
            mList[it.key-1+startDay] = Triple(DateInfo(tempDate,1),mList[it.key-1+startDay].second,true)
        }
    }

}