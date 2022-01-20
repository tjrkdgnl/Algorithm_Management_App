package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.R

class FakeNetWorkDataProvider(private val mApp: Application) {
    private val mProblemId = mApp.getString(R.string.default_problemId).toInt()
    private val mCount = mApp.getString(R.string.default_count).toInt()

    fun getProblem(problemId: Int): TaggedProblem {
        return TaggedProblem(
            problemId, "A+B", true, false, 151801, 1, 17, true, 2.333,
            mutableListOf(
                Tag(
                    "arithmetic",
                    false,
                    121,
                    494,
                    mutableListOf(DisplayName("en", "arithmetic", "arithmetic"))
                )
            )
        )
    }

    fun getSolvedAlgorithms(userId: String): Problems {
        return Problems(
            mCount, MutableList(mCount) {
                TaggedProblem(
                    it + mProblemId, "A+B", true, false, 151801, it + 1, 17, true, it + 2.333,
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
            mCount, MutableList(mCount) {
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
        return MutableList<Stats>(mCount + 1) {
            Stats(it, it + 116, it + 5, 0, it, it + 3360)
        }
    }

    fun getProblemStatsList(): List<ProblemStatus> {
        return MutableList<ProblemStatus>(mCount) {
            ProblemStatus(it + mProblemId, if (it > 10) "tried" else "solved")
        }
    }
}