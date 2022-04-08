package com.ama.algorithmmanagement.domain.entity

data class UserInfo(
    val userId :String,
    val userPw: String,
    val fcmToken :String?
){
    constructor() :this("","","")
}

