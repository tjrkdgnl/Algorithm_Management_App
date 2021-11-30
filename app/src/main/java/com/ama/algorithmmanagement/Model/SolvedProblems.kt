package com.ama.algorithmmanagement.Model

data class SolvedProblems(
    val count: Int,
    val userId: String,
    val problemList: List<TaggedProblem>
)