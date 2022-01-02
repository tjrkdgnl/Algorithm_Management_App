package com.ama.algorithmmanagement.Model

data class IdeaInfos(
    var count: Int,
    val problemId: Int,
    val ideaList: MutableList<IdeaInfo>
) {
    constructor() : this(0, 0, mutableListOf())

}
