package com.ama.algorithmmanagement.data.model

data class YearInfo(
    val year: Int,
    val monthInfoList: MutableList<MonthInfo>
) {
    constructor() : this(0, mutableListOf())

}
