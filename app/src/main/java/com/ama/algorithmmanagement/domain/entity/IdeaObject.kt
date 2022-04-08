package com.ama.algorithmmanagement.domain.entity

data class IdeaObject(
    val userId: String,
    var count: Int,
    val ideaInfosList: MutableList<IdeaInfos>
) {
    constructor() : this("", 0, mutableListOf())


    fun toMap(): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["userId"] = userId
        map["count"] = count
        map["ideaInfosList"] = ideaInfosList
        return map
    }

}
