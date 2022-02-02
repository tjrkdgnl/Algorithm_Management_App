package com.ama.algorithmmanagement.utils;

import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.model.TaggedProblem
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry

import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter


/**
 * @author : seungHo
 * @since : 2021-12-29
 * class : MpChartUtils.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : mpChart 라이브러리 관련 함수 및 클래스 정의
 */
object MpChartUtils {
    class LabelCustomFormatter(private val labels:List<String>) : ValueFormatter() {
        private var index = 0

        override fun getFormattedValue(value: Float): String {
            index = value.toInt()
            return labels[index]
        }

        override fun getBarStackedLabel(value: Float, stackedEntry: BarEntry?): String {
            return super.getBarStackedLabel(value, stackedEntry)
        }
    }

    class ScoreCustomFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val score = value.toString().split(".")
            return score[0]
        }
    }
    fun getTypeStats(problems: Problems?):HashMap<String,Int>?{
        val hashMap = HashMap<String,Int>()
        val problemList = problems?.problemList
        problemList?.map {  taggedProblem ->
            taggedProblem.tags.map { Tag->
                Tag.displayNames.map { displayName ->
                    if(hashMap.containsKey(displayName.name)){
                        hashMap[displayName.name]?.let{
                            hashMap[displayName.name] = it+1
                        }
                    }else{
                        hashMap[displayName.name] = 1
                    }
                }
            }
        }
        return hashMap
    }
}
