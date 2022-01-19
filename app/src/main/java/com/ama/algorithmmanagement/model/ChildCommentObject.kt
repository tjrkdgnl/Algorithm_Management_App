package com.ama.algorithmmanagement.model

data class ChildCommentObject(
    var count: Int,
    val commentId: String,
    val commentChildList: MutableList<ChildCommentInfo>
) {

    constructor() : this(0, "", mutableListOf())

    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["count"] = count
        map["commentId"] = commentId
        map["commentChildList"] = commentChildList
        return map
    }

}
