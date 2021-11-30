package com.ama.algorithmmanagement.Model

data class CommentInfos(
    val count: Int,
    val problemId: String,
    val commentList: List<CommentInfo>
)
