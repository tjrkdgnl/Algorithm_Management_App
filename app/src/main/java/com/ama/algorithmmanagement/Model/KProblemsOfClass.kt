package com.ama.algorithmmanagement.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KProblemsOfClass(
    @SerializedName("class")
    @Expose
    var _class: Int?,

    @SerializedName("total")
    @Expose
    var total: Int?,

    @SerializedName("essentials")
    @Expose
    var essentials: Int?
)