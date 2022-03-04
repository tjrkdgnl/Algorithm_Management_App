package com.ama.algorithmmanagement.data.model

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
