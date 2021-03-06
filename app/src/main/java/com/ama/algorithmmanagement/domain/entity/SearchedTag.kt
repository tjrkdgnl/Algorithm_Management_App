package com.ama.algorithmmanagement.domain.entity

data class SearchedTag(
    val caption: String,
    val description: String,
    val href: String,
    val key: String,
    val name: String,
    val problemCount: Int
)