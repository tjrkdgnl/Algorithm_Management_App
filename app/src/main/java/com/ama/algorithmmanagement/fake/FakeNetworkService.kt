package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.model.ProblemStatus
import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.model.Stats
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.delay

class FakeNetworkService(private val mFakeNetWorkDataProvider: FakeNetWorkDataProvider) :
    BaseNetworkService {
    override suspend fun getProblem(problemId: Int): TaggedProblem {
        delay(2000)
        return mFakeNetWorkDataProvider.getProblem(problemId)
    }

    override suspend fun getSolvedProblems(userId: String): Problems {
        delay(2000)
        //solvedby 붙여서 검색해야함
        return mFakeNetWorkDataProvider.getSolvedAlgorithms(userId)
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        delay(2000)
        //부분 problem id or problem id
        return mFakeNetWorkDataProvider.getSearchProblemList(problemId)
    }

    override suspend fun getUserStats(userId: String): List<Stats> {
        delay(2000)
        return mFakeNetWorkDataProvider.getStatsList()
    }

    override suspend fun getUnSolvedProblems(solvedacToken: String): List<ProblemStatus> {
        delay(2000)
        return mFakeNetWorkDataProvider.getProblemStatsList()
    }

}