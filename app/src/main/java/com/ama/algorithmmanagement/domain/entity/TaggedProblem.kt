package com.ama.algorithmmanagement.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable {
    constructor() : this(0, "", false, false, 0, 0, 0, false, 0.0, mutableListOf())
}