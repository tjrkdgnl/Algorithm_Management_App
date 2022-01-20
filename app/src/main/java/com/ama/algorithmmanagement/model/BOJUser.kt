package com.ama.algorithmmanagement.model

data class BOJUser(
    val user: User,
    val solved: List<ProblemStatus>
)