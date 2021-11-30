package com.ama.algorithmmanagement.Model

data class Tag(
    val bojTagId: Int,
    val displayNames: List<DisplayName>,
    val isMeta: Boolean,
    val key: String,
    val problemCount: Int
)