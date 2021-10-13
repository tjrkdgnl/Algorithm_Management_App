package com.ama.algorithmmanagement.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class KProblemsOfClass {
    @SerializedName("class")
    @Expose
    var _class: Int? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("essentials")
    @Expose
    var essentials: Int? = null

}