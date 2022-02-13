package com.ama.algorithmmanagement.utils;

import android.app.Application
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.R

/**
 * @author : seungHo
 * @since : 2021-12-28
 * class : TierColors.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 색상관련 유틸함수
 */
object ColorUtils {
    // 나의 실력그래프 티어별 색상 ENUM
    enum class TierTypes{
        BRONZE,SILVER,GOLD,PLATINUM,DIAMOND,RUBY
    }
    // 나의 실력 그래프 날짜별 통계 색상 ENUM
    enum class DateInfoTypes{
        ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NO
    }

    // 날짜별 통계에 사용될 색상 리스트로 묶음
    val dateInfoColors = listOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_0),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_1),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_2),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_3),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_4),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_6),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_7),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_8),
        ContextCompat.getColor(AMAApplication.INSTANCE,R.color.date_blank),
    )
/*    fun getDateInfoType(value:Int):DateInfoTypes{
        return when(value){
            -1->DateInfoTypes.NO
            0->DateInfoTypes.ZERO
            1->DateInfoTypes.ONE
            2->DateInfoTypes.TWO
            3->DateInfoTypes.THREE
            4->DateInfoTypes.FOUR
            5->DateInfoTypes.FIVE
            6->DateInfoTypes.SIX
            7->DateInfoTypes.SEVEN
            8->DateInfoTypes.EIGHT
            else->DateInfoTypes.NO
        }
    }*/


    private val bronzeList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_1),
    )
    private val silverList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_1),
    )
    private val goldList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_1),
    )
    private val platinumList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_1),
    )
    private val diamondList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_1),
    )
    private val rubyList = listOf(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_1),
    )

    // 해당 티어의 Enum 을 넘기면 넘긴 정보를 문자로 리턴
    fun getTierName(tier: TierTypes): String {
        return when (tier) {
            TierTypes.BRONZE -> "bronze"
            TierTypes.SILVER -> "silver"
            TierTypes.GOLD -> "gold"
            TierTypes.PLATINUM -> "platinum"
            TierTypes.DIAMOND -> "diamond"
            TierTypes.RUBY -> "ruby"
            else -> "none"
        }
    }
    // 정수를 넘기면 Enum 으로 리턴
    fun tierConvertInt(pos:Int):TierTypes{
        return when(pos){
            0->TierTypes.BRONZE
            1->TierTypes.SILVER
            2->TierTypes.GOLD
            3->TierTypes.PLATINUM
            4->TierTypes.DIAMOND
            5->TierTypes.RUBY
            else->TierTypes.BRONZE
        }

    }
    // 티어 enum 을 넘기면 해당 티어의 색상코드 리스트를 넘김
    fun getTierList(tier: TierTypes): List<Int> {
        return when (tier) {
            TierTypes.BRONZE -> bronzeList
            TierTypes.SILVER -> silverList
            TierTypes.GOLD -> goldList
            TierTypes.PLATINUM -> platinumList
            TierTypes.DIAMOND -> diamondList
            TierTypes.RUBY -> rubyList
            else -> bronzeList
        }
    }

    // 정수를 넘기면 색상값으로 리턴
    fun dateInfoConvert(count:Int):Int{
        return when(count){
            -1->dateInfoColors[8]
            0->dateInfoColors[0]
            1->dateInfoColors[1]
            2->dateInfoColors[2]
            3->dateInfoColors[3]
            4->dateInfoColors[4]
            5->dateInfoColors[5]
            6->dateInfoColors[6]
            7->dateInfoColors[7]
            else -> dateInfoColors[7]
        }

    }
}