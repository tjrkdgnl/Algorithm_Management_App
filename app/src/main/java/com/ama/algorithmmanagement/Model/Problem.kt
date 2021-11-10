package com.ama.algorithmmanagement.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Problem(
    @SerializedName("problemId")
    @Expose
    var problemId: Int?,

    @SerializedName("titleKo")
    @Expose
    var titleKo: String?,

    @SerializedName("isSolvable")
    @Expose
    var isSolvable: Boolean?,

    @SerializedName("isPartial")
    @Expose
    var isPartial: Boolean?,

    @SerializedName("acceptedUserCount")
    @Expose
    var acceptedUserCount: Int?,

    @SerializedName("level")
    @Expose
    var level: Int?,

    @SerializedName("votedUserCount")
    @Expose
    var votedUserCount: Int?,

    @SerializedName("isLevelLocked")
    @Expose
    var isLevelLocked: Boolean?,

    @SerializedName("averageTries")
    @Expose
    var averageTries: Double?
)