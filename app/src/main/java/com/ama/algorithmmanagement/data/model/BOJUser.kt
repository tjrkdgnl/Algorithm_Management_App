package com.ama.algorithmmanagement.data.model

data class BOJUser(
    val user: User,
    var solved: List<ProblemStatus>
)