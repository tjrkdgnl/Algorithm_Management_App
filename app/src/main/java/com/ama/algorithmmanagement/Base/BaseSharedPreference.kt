package com.ama.algorithmmanagement.Base


interface BaseSharedPreference {

    fun getUserIdFromLocal(): String?

    fun delete()

    fun setUserIdToLocal(userId: String)

}