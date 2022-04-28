package com.ama.algorithmmanagement.utils

import com.ama.algorithmmanagement.data.firebase.SortEnum

object ProblemUtils {
    val sortEnumArray = arrayOf("최신순","오래된순","쉬운순","어려운순",)
    fun convertSortEnumToString(sort:SortEnum):String{
        when(sort){
            SortEnum.EASY->{
                return "쉬운순"
            }
            SortEnum.HARD->{
                return "어려운순"
            }
            SortEnum.LATEST->{
                return "최신순"
            }
            SortEnum.PAST->{
                return "오래된순"
            }
            else->{
                return ""
            }
        }

    }
    fun convertIntToSortEnum(value:Int):SortEnum?{
        return when(value){
            0->SortEnum.LATEST
            1->SortEnum.PAST
            2->SortEnum.EASY
            3->SortEnum.HARD
            else->null
        }
    }
}