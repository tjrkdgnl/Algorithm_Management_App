package com.ama.algorithmmanagement.utils;

import androidx.core.content.ContextCompat
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import timber.log.Timber

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
    enum class TierTypes {
        BRONZE, SILVER, GOLD, PLATINUM, DIAMOND, RUBY
    }

    // 나의 실력 그래프 날짜별 통계 색상 ENUM
    enum class DateInfoTypes {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NO
    }

    // 날짜별 통계에 사용될 색상 리스트로 묶음
    val dateInfoColors = listOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_0),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_1),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.date_base),
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
    fun tierConvertInt(pos: Int): TierTypes {
        return when (pos) {
            0 -> TierTypes.BRONZE
            1 -> TierTypes.SILVER
            2 -> TierTypes.GOLD
            3 -> TierTypes.PLATINUM
            4 -> TierTypes.DIAMOND
            5 -> TierTypes.RUBY
            else -> TierTypes.BRONZE
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
    fun dateInfoConvert(count: Int): Int {
        Timber.e("count :$count")
        return if (count == -1 || count == 0) {
            dateInfoColors[0]
        } else if (count in (1..3)) {
            dateInfoColors[1]
        } else if (count in (4..9)) {
            dateInfoColors[2]
        } else if (count in (10..18)) {
            dateInfoColors[3]
        } else if (count in (19..29)) {
            dateInfoColors[4]
        }else{
            dateInfoColors[4]
        }
    }

    fun intConvertToTier(data: Int): String {
        // 각 티어는 브론즈 5,4,3,2,1 다음티어가 실버5 이런식으로 5부터 까지 가고 1 다음티어 로 가게됨
        // 티어 0은 unranked 1에서 30까지 브론즈에서 루비까지 0부터 계산하기위해  data 에 1을 뺴서 -1 은 unranked = -1
        // 0~29 는 브론즈~ 루비티어가됨 나누기 5를 하면 0~4(브론즈)/5 는 0 5~9(실버)/5 는 1 이런식으로 나눠지게됨
        // data -=1
        // data 0, data 1, data 2, data 3, data 4  =>(0~4)/5=0 (0+1)*5  브론즈 5-0 브론즈 5-1 브론즈 5-2 브론즈 5-3 브론즈 5-4
        // data 5, data 6, data 7, data 8, data 9 = >(5~9)/5=1 (1+1)*5 실버 10-5 실버 10-6 실버 10-7 실버 10-8 실버 10-9
        // ...
        val tierData = (((data - 1) / 5 + 1) * 5 - (data - 1))

        return when (data) {
            0 -> "Unranked"
            1, 2, 3, 4, 5 -> "브론즈 $tierData"
            6, 7, 8, 9, 10 -> "실버 $tierData"
            11, 12, 13, 14, 15 -> "골드 $tierData"
            16, 17, 18, 19, 20 -> "플래티넘 $tierData"
            21, 22, 23, 24, 25 -> "다이아 $tierData"
            26, 27, 28, 29, 30 -> "루비 $tierData"
            else -> "알수없음"
        }
    }
}