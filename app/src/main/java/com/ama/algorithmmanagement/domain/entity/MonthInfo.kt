package com.ama.algorithmmanagement.domain.entity

data class MonthInfo(
    val month: Int,
    var count: Int,
    val dateList: MutableList<DateInfo>
) {
    constructor() : this(0, 0, mutableListOf())
}