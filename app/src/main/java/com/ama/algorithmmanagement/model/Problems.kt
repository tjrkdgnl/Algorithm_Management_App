package com.ama.algorithmmanagement.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Problems(
    @SerializedName("count")
    @Expose
    var count: Int?,

    @SerializedName("items")
    @Expose
    var problemList: MutableList<TaggedProblem>?
)