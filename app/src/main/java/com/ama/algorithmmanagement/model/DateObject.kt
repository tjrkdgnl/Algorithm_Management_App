package com.ama.algorithmmanagement.model

data class DateObject(
    val userId: String,
    val yearInfo:MutableList<YearInfo>
) {
    constructor() : this("", mutableListOf())

    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["userId"] = userId
        map["year"] = yearInfo
        return map
    }

}
