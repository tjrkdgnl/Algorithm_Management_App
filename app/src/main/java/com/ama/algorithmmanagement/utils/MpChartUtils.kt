package com.ama.algorithmmanagement.utils;

import com.ama.algorithmmanagement.data.model.TaggedProblem
import com.github.mikephil.charting.data.BarEntry

import com.github.mikephil.charting.formatter.ValueFormatter
import timber.log.Timber


/**
 * @author : seungHo
 * @since : 2021-12-29
 * class : MpChartUtils.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : mpChart 라이브러리 관련 함수 및 클래스 정의
 */
object MpChartUtils {
    // x 축 값에서 레이블 지정( horizontal bar 의 경우 y 축)
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

    // bar 마다 값을 표시해줌
    class ScoreCustomFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            // float 형태이기 떄문에 소수점 짜르고 정수부분만 출력
            val score = value.toString().split(".")
            return "${score[0]} 점"
        }
    }

    // 유형별로 저장된 해시맵에서 몇개씩 뿌려줄건지 지정
    fun getTypeStatsSize(problemList:MutableList<TaggedProblem>,size:Int=10,isAll:Boolean=true):HashMap<String,Int>{
        Timber.e("prombel ${problemList.size}")
        val getHashMap = getTypeStats(problemList)
        Timber.e("get hashmap $getHashMap")
        if(isAll){
            Timber.e("????")
            return getHashMap
        }
        val sizeHashMap = HashMap<String,Int>()
        val typeList = getHashMap.keys.toList()
        Timber.e("typeList : $typeList")
        val range = if(typeList.size>=size)size else typeList.size

        if(getHashMap.isNotEmpty()){
            Timber.e("$size")
            for(i in 0..range-1){
                Timber.e("$i : ${getHashMap[typeList[i]]}")
                getHashMap[typeList[i]]?.let {
                    sizeHashMap.put(typeList[i], it)
                }
            }
        }
        Timber.e("sizehashmap $sizeHashMap")
        return sizeHashMap

    }

    // 유형별로 몇개 풀었는지 해시맵에 저장
    private fun getTypeStats(problemList: MutableList<TaggedProblem>?):HashMap<String,Int>{
        val hashMap = HashMap<String,Int>()
        problemList?.map {  taggedProblem ->
            taggedProblem.tags.map { Tag->
                Tag.displayNames.map { displayName ->
                    // 태그가 한국어인경우만 통계 냄
                    if(displayName.language=="ko"){
                        if(hashMap.containsKey(displayName.name)){
                            // 해시맵에 키가 존재할경우 1씩 증가시키고 아닐경우 1로 세팅
                            hashMap[displayName.name]?.let {
                                hashMap[displayName.name] = it + 1
                            }

                        }else{
                            Timber.e("name :${displayName.name}")
                            hashMap[displayName.name] = 1
                        }
                    }
                }
            }
        }
        return hashMap
    }
}
