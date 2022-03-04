package com.ama.algorithmmanagement.data.model

data class TippingProblemObject(
    var count: Int,
    val userId: String,
    val problemInfoList: MutableList<TipProblemInfo>
) {
    constructor():this(0,"", mutableListOf())


    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["count"] = count
        map["userId"] = userId
        map["problemInfoList"] = problemInfoList
        return map
    }

}