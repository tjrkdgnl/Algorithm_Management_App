package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.Problems


interface BaseSharedPreference {

    fun getUserId(): String?

    fun setAutoLoginCheck(check:Boolean)

    fun getAutoLoginCheck(): Boolean

    fun deleteUserId()

    fun setUserId(userId: String)

    fun setTierType(tierType: Int)

    fun getTierType(): Int?

    fun deleteToTierType()

    fun setSolvedacToken(solvedacToken:String)

    fun getSolvedacToken(): String?

    fun deleteSolvedacToken()

    fun setSolvedProblems(solvedProblem:Problems)

    fun getSolvedProblems():Problems?

    fun deleteSolvedProblems()

}