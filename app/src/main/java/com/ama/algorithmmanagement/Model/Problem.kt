package com.ama.algorithmmanagement.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Problem {
    @SerializedName("problemId")
    @Expose
    var problemId: Int? = null

    @SerializedName("titleKo")
    @Expose
    var titleKo: String? = null

    @SerializedName("isSolvable")
    @Expose
    var isSolvable: Boolean? = null

    @SerializedName("isPartial")
    @Expose
    var isPartial: Boolean? = null

    @SerializedName("acceptedUserCount")
    @Expose
    var acceptedUserCount: Int? = null

    @SerializedName("level")
    @Expose
    var level: Int? = null

    @SerializedName("votedUserCount")
    @Expose
    var votedUserCount: Int? = null

    @SerializedName("isLevelLocked")
    @Expose
    var isLevelLocked: Boolean? = null

    @SerializedName("averageTries")
    @Expose
    var averageTries: Double? = null
}