package com.ama.algorithmmanagement.Model

data class IdeaInfo(
    val url :String?,
    val comment :String?,
    val date :String
) {
    constructor() : this(null,null,"")
}
