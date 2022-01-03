package com.ama.algorithmmanagement.Model

data class DateInfoObject(
    var count: Int,
    val userId: String,
    val dateList: MutableList<DateInfo>
) {
    constructor() : this(0, "", mutableListOf())

    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["count"] = count
        map["userId"] = userId
        map["dateList"] = dateList
        return map
    }

}
