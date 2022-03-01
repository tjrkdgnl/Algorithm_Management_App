package com.ama.algorithmmanagement.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data Parsing Class
 */
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