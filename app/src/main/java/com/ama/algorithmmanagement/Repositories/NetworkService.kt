package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.Network.KAPIGenerator
import com.ama.algorithmmanagement.model.ProblemStatus
import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.model.Stats
import com.ama.algorithmmanagement.model.TaggedProblem
import retrofit2.await

class NetworkService : BaseNetworkService {

    override suspend fun getProblem(problemId: Int): TaggedProblem {
        return KAPIGenerator.getInstance().getProblem(problemId)
    }

    override suspend fun getSolvedProblems(userId: String): Problems {
        val solvedProblems = Problems(0, mutableListOf())
        var page = 1

        while (true) {
            val data = KAPIGenerator.getInstance().getSolvedProblems(userId, null, page, null)

            if (data.problemList == null) {
                break
            }
            if (data.problemList?.size == 0) {
                break
            }

            solvedProblems.problemList?.addAll(data.problemList!!)
            page++
        }

        return solvedProblems
    }

    override suspend fun getUserStats(userId: String): List<Stats> {
        return KAPIGenerator.getInstance().getUserStatsInfo(userId).await()
    }

    override suspend fun getUnSolvedProblems(solvedacToken: String): List<ProblemStatus> {
        KAPIGenerator.initRetrofit(solvedacToken)

        val response = KAPIGenerator.getInstance().getBOJUserInfo()

        return response.solved.filter { it.status == "tried" }.toMutableList()
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        return KAPIGenerator.getInstance().getSearchProblemList(problemId)
    }

}