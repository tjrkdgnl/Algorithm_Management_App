package com.ama.algorithmmanagement.model

data class CommentObject(
    var count: Int,
    val problemId: Int,
    val commentList: MutableList<CommentInfo>
) {
    constructor() : this(1, 0, mutableListOf())

    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["count"] = count
        map["problemId"] = problemId
        map["commentList"] = commentList

        return map
    }
}
