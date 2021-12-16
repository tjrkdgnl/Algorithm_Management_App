package com.ama.algorithmmanagement.Model

data class CommentObject(
    val count: Int,
    val problemId: Int,
    val commentList: MutableList<CommentInfo>
)
