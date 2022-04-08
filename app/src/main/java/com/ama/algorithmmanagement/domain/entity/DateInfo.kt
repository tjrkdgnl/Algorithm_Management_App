package com.ama.algorithmmanagement.domain.entity

data class DateInfo(
    val date: String,
    val count: Int
) {
    constructor() : this("",0)
}
