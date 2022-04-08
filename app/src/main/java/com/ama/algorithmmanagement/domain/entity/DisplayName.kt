package com.ama.algorithmmanagement.domain.entity

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