package com.ama.algorithmmanagement.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisplayName(
    val language: String,
    val name: String,
    val short: String
) : Parcelable {
    constructor() : this("", "", "")
}