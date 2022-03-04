package com.ama.algorithmmanagement.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AutoKeywordObject(
    val autocomplete: List<Autocomplete>,
    val problemCount: Int,
    @SerializedName("problems")
    @Expose
    val keywords: List<Keyword>,
    val tagCount: Int,
    val searchedTags: List<SearchedTag>,
    val userCount: Int,
    val users: List<Any>
)