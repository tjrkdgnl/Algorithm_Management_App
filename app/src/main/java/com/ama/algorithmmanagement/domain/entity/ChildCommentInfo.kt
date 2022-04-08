package com.ama.algorithmmanagement.domain.entity

data class ChildCommentInfo(
    val userId: String,
    val tierType: Int,
    val comment: String,
    val date: String
) {
    constructor() : this("", 0, "", "")
}
