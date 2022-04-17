package com.ama.algorithmmanagement.utils

import com.ama.algorithmmanagement.data.firebase.SortEnum
import com.ama.algorithmmanagement.domain.entity.TippingProblemObject


fun TippingProblemObject.sort(type: SortEnum) {
    when (type) {
        SortEnum.LATEST -> {
            problemInfoList.sortWith { o1, o2 ->
                val datesOfO1 = o1.date.split(".")
                val yearOfO1 = datesOfO1[0]
                val monthOfO1 = datesOfO1[1]
                val dayOfO1 = datesOfO1[2]

                val datesOfO2 = o2.date.split(".")
                val yearOfO2 = datesOfO2[0]
                val monthOfO2 = datesOfO2[1]
                val dayOfO2 = datesOfO2[2]

                when {
                    yearOfO1 < yearOfO2 -> 1
                    yearOfO1 == yearOfO2 -> {
                        when {
                            monthOfO1 < monthOfO2 -> 1
                            monthOfO1 == monthOfO2 -> dayOfO2.compareTo(dayOfO1)
                            else -> -1
                        }
                    }
                    else -> -1
                }
            }
        }
        SortEnum.PAST -> {
            problemInfoList.sortWith { o1, o2 ->
                val datesOfO1 = o1.date.split(".")
                val yearOfO1 = datesOfO1[0]
                val monthOfO1 = datesOfO1[1]
                val dayOfO1 = datesOfO1[2]

                val datesOfO2 = o2.date.split(".")
                val yearOfO2 = datesOfO2[0]
                val monthOfO2 = datesOfO2[1]
                val dayOfO2 = datesOfO2[2]

                when {
                    yearOfO1 < yearOfO2 -> -1
                    yearOfO1 == yearOfO2 -> {
                        when {
                            monthOfO1 < monthOfO2 -> -1
                            monthOfO1 == monthOfO2 -> dayOfO1.compareTo(dayOfO2)
                            else -> 1
                        }
                    }
                    else -> 1
                }
            }
        }
        SortEnum.HARD -> {
            problemInfoList.sortWith { o1, o2 ->
                o1.problem?.let { problem1 ->
                    o2.problem?.level?.compareTo(problem1.level)
                } ?: 0
            }
        }
        SortEnum.EASY -> {
            problemInfoList.sortWith { o1, o2 ->
                o2.problem?.let { problem2 ->
                    o1.problem?.level?.compareTo(problem2.level)
                } ?: 0
            }
        }
    }
}