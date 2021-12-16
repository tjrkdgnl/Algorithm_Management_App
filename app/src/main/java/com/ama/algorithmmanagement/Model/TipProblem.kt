package com.ama.algorithmmanagement.Model

data class TipProblem(
    val problem: TaggedProblem,
    var isShow: Boolean,
    var tipComment: String?,
    val date: String
)
