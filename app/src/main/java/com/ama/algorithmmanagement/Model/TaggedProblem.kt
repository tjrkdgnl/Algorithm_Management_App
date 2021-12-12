package com.ama.algorithmmanagement.Model

data class TaggedProblem(
    val problemId: Int,
    val titleKo: String,
    val isSolvable: Boolean,
    val isPartial: Boolean,
    val acceptedUserCount: Int,
    val level: Int,
    val votedUserCount: Int,
    val isLevelLocked: Boolean,
    val averageTries: Double,
    val tags: List<Tag>
)