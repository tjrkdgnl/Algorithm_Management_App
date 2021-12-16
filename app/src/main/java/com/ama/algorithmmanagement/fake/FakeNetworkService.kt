package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.BaseNetworkService
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

    override suspend fun getBOJUserInfo(): List<ProblemStatus> {
        delay(2000)
        return mFakeNetWorkDataProvider.getProblemStatsList()
    }

}