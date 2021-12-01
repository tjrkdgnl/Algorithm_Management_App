package com.ama.algorithmmanagement.Model

data class IdeaInfos(
    val count: Int,
    val problemId: Int,
    val ideaList: MutableList<IdeaInfo>
)
