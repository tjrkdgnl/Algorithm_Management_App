package com.ama.algorithmmanagement.base

import com.ama.algorithmmanagement.model.Problems


interface BaseSharedPreference {

    fun getUserId(): String?

    fun deleteUserId()

    fun setUserId(userId: String)

    fun setAutoLoginCheck(check:Boolean)

    fun getAutoLoginCheck(): Boolean

    fun deleteAutoLoginCheck()

    fun setTierType(tierType: Int)

    fun getTierType(): Int?

    fun deleteToTierType()

    fun setSolvedacToken(solvedacToken:String)

    fun getSolvedacToken(): String?

    fun deleteSolvedacToken()

    fun setSolvedProblems(solvedProblems:Problems)

    fun getSolvedProblems():Problems?

    fun deleteSolvedProblems()

}