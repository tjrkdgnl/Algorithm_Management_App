package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.*

class FakeNetWorkDataProvider(private val mFakeSharedPreference: FakeSharedPreference) {

    fun getSolvedAlgorithms(userId: String): Problems {
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

    fun getSearchProblemList(problemId: Int): Problems {
        return Problems(
            30, MutableList(30) {
                TaggedProblem(
                    it + problemId, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
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
            Stats(it, it + 116, it + 5, 0, it, it + 3360)
        }
    }

    fun getProblemStatsList(): List<ProblemStatus> {
        return MutableList<ProblemStatus>(20) {
            ProblemStatus(it + 1000, if (it > 10) "tried" else "solved")
        }
    }
}