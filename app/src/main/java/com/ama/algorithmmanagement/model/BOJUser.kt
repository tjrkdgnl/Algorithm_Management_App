package com.ama.algorithmmanagement.model

data class BOJUser(
    val user: User,
    var solved: List<ProblemStatus>
)