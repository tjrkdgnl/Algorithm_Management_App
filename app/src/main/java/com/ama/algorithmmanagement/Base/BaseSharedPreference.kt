package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Model.TaggedProblem


interface BaseSharedPreference {

    fun getUserIdFromLocal(): String?

    fun deleteToUserId()

    fun setUserIdToLocal(userId: String)

    fun setTierType(tierType: Int)

    fun getTierType(): Int?

    fun deleteToTierType()

    fun setSolvedacToken(solvedacToken:String)

    fun getSolvedacToken(): String?

    fun setSolvedProblems(solvedProblem:Problems)

    fun getSolvedProblems():Problems?

}