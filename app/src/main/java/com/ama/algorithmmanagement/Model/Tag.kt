package com.ama.algorithmmanagement.Model

data class Tag(
    val key: String,
    val isMeta: Boolean,
    val bojTagId: Int,
    val problemCount: Int,
    val displayNames: List<DisplayName>
)