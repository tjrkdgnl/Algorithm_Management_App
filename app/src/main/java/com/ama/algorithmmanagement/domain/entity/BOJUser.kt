package com.ama.algorithmmanagement.domain.entity

data class BOJUser(
    val user: User,
    var solved: List<ProblemStatus>
)