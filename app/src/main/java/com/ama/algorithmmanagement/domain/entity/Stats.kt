package com.ama.algorithmmanagement.domain.entity

data class Stats(
    val level: Int,
    val total: Int,
    val solved: Int,
    val partial: Int,
    val tried: Int,
    val exp: Int
)