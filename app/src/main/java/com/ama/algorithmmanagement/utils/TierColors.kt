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
 * description : 티어별 색상 코드리스트
 */
object TierColors {
    // enum 으로 변경
    enum class TierTypes{
        BRONZE,SILVER,GOLD,PLATINUM,DIAMOND,RUBY
    }

    const val BRONZE = 0
    const val SILVER = 1
    const val GOLD = 2
    const val PLATINUM = 3
    const val DIALMOND = 4
    const val RUBY = 5
    val bronzeList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.bronze_1),
    )
    val silverList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.silver_1),
    )
    val goldList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.gold_1),
    )
    val platinumList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.platinum_1),
    )
    val diamondList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.diamond_1),
    )
    val rubyList = mutableListOf<Int>(
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_5),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_4),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_3),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_2),
        ContextCompat.getColor(AMAApplication.INSTANCE, R.color.ruby_1),
    )

    fun getTierName(tier: Int): String {
        return when (tier) {
            BRONZE -> "bronze"
            SILVER -> "silver"
            GOLD -> "gold"
            PLATINUM -> "platinum"
            DIALMOND -> "diamond"
            RUBY -> "ruby"
            else -> "none"
        }
    }

    fun getTierList(tier: Int): MutableList<Int>? {
        return when (tier) {
            BRONZE -> bronzeList
            SILVER -> silverList
            GOLD -> goldList
            PLATINUM -> platinumList
            DIALMOND -> diamondList
            RUBY -> rubyList
            else -> null
        }
    }
}