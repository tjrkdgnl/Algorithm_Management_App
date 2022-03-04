package com.ama.algorithmmanagement.data.model

data class UserInfo(
    val userId :String,
    val userPw: String,
    val fcmToken :String?
){
    constructor() :this("","","")
}

