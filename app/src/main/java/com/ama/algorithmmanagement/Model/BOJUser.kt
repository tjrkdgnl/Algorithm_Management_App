package com.ama.algorithmmanagement.Model

data class BOJUser(
    val user: User,
    val solved: List<ProblemStatus>
)