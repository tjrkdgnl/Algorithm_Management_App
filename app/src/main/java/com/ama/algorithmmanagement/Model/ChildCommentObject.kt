package com.ama.algorithmmanagement.Model

data class ChildCommentObject(
    val count: Int,
    val commentId: String,
    val commentChildList: MutableList<ChildCommentInfo>
)
