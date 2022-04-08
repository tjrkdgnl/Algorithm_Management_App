package com.ama.algorithmmanagement.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipProblemInfo(
    val problem: TaggedProblem?,
    var isShow: Boolean,
    var tipComment: String?,
    val date: String
) : Parcelable {
    constructor() : this(null, false, null, "")
}
