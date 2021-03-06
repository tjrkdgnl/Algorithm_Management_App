package com.ama.algorithmmanagement.domain.entity

data class Keyword(
    val caption: String,
    val description: String,
    val href: String,
    val id: Int,
    val level: Int,
    val solved: Int,
    val title: String
)