package com.ama.algorithmmanagement.model

data class ChildCommentInfo(
    val userId: String,
    val tierType: Int,
    val comment: String,
    val date: String
) {
    constructor() : this("", 0, "", "")
}