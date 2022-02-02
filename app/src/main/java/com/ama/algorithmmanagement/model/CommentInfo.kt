package com.ama.algorithmmanagement.model

data class CommentInfo(
    val commentId: String,
    val userId: String,
    val tierType: Int,
    val comment: String,
    val date: String,
    val commentChildCount: Int
) {
    constructor() : this("", "", 0, "", "", 0)


}