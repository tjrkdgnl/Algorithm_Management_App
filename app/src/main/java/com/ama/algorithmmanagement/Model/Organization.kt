package com.ama.algorithmmanagement.Model

data class Organization(
    val color: String,
    val name: String,
    val organizationId: Int,
    val rating: Int,
    val solvedCount: Int,
    val type: String,
    val userCount: Int,
    val voteCount: Int
)