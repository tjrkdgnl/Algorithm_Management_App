package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.Model.ProblemStatus
import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Model.Stats
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.Network.KAPIGenerator
import retrofit2.await

class NetworkService : BaseNetworkService {

    override suspend fun getProblem(problemId: Int): TaggedProblem {
        return KAPIGenerator.getInstance().getProblem(problemId)
    }

    override suspend fun getSolvedProblems(userId: String): Problems {
        return KAPIGenerator.getInstance().getSolvedProblems(userId)
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