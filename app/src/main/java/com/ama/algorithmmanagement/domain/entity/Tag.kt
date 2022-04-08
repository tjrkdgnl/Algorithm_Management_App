package com.ama.algorithmmanagement.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val key: String,
    val isMeta: Boolean,
    val bojTagId: Int,
    val problemCount: Int,
    val displayNames: List<DisplayName>
):Parcelable {

    constructor() :this("",false,0,0, mutableListOf())
}