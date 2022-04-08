package com.ama.algorithmmanagement.domain.entity

data class CommentInfo(
    val problemId: Int,
    val commentId: String,
    val userId: String,
    val tierType: Int,
    val comment: String,
    val date: String,
    val commentChildCount: Int
) {
    constructor() : this(-1,"", "", -1, "", "", -1)


}
