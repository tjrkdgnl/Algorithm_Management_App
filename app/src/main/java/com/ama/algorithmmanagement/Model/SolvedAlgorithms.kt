package com.ama.algorithmmanagement.Model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SolvedAlgorithms {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("items")
    @Expose
    var problemList: MutableList<Problem>? = null
}