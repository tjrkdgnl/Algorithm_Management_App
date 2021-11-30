package com.ama.algorithmmanagement.Model

data class CommentChildInfos(
    val count: Int,
    val commentId: String,
    val commentChildList: List<CommentChildInfo>
)
