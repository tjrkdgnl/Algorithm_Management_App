package com.ama.algorithmmanagement.Model

data class TaggedProblem(
    val acceptedUserCount: Int,
    val averageTries: Double,
    val isLevelLocked: Boolean,
    val isPartial: Boolean,
    val isSolvable: Boolean,
    val level: Int,
    val problemId: Int,
    val tags: List<Tag>,
    val titleKo: String,
    val votedUserCount: Int
)