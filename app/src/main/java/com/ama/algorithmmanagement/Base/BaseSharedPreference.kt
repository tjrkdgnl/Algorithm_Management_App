package com.ama.algorithmmanagement.Base


interface BaseSharedPreference {

    fun getUserIdFromLocal(): String?

    fun deleteToUserId()

    fun setUserIdToLocal(userId: String)

    fun setTierType(tierType: Int)

    fun getTierType(): Int?

    fun deleteToTierType()

    fun setSolvedacToken(solvedacToken:String)

    fun getSolvedacToken(): String?

}