package com.ama.algorithmmanagement.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SolvedAlgorithms(
    @SerializedName("count")
    @Expose
    var count: Int?,

    @SerializedName("items")
    @Expose
    var problemList: MutableList<Problem>?
)