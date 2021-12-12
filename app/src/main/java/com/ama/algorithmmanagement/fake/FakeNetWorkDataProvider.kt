package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.*

class FakeNetWorkDataProvider {

    fun getSolvedAlgorithms(): Problems {
        return Problems(
            30, MutableList(30) {
                TaggedProblem(
                    it + 1000, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
                    mutableListOf(
                        Tag(
                            "arithmetic",
                            false,
                            it + 121,
                            it + 494,
                            mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                        )
                    )
                )
            }
        )
    }

    fun getSearchProblemList(problemId: String): Problems {
        return Problems(
            30, MutableList(30) {
                TaggedProblem(
                    it + 1000, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
                    mutableListOf(
                        Tag(
                            "arithmetic",
                            false,
                            it + 121,
                            it + 494,
                            mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                        )
                    )
                )
            }
        )
    }


    fun getStatsList(): List<Stats> {
        return MutableList<Stats>(20) {
            Stats(it, it + 116, it + 5, 0, 0, it + 3360)
        }
    }

    fun getProblemStatsList(): List<ProblemStatus> {
        return MutableList<ProblemStatus>(20) {
            ProblemStatus(it + 1000, "tried")
        }
    }
}