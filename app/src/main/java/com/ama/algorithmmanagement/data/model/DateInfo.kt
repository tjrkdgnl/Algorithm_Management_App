package com.ama.algorithmmanagement.data.model

data class DateInfo(
    val date: String,
    val count: Int
) {
    constructor() : this("",0)
}
