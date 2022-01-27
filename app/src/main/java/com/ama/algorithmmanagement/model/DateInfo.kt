package com.ama.algorithmmanagement.model

data class DateInfo(
    val date: String,
    val count: Int
) {
    constructor() : this("",0)
}
