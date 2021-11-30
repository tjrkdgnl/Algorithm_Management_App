package com.ama.algorithmmanagement.Model

data class TipProblem(
    val problem: TaggedProblem,
    val isShow: Boolean,
    val tipComment: String?,
    val date: String,
    val problemType: String
)
