package com.ama.algorithmmanagement.domain.entity

data class YearInfo(
    val year: Int,
    val monthInfoList: MutableList<MonthInfo>
) {
    constructor() : this(0, mutableListOf())

}
