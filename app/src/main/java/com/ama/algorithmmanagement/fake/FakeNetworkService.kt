package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.model.*
import kotlinx.coroutines.delay

class FakeNetworkService(private val mFakeNetWorkDataProvider: FakeNetWorkDataProvider) :
    BaseNetworkService {
    override suspend fun getProblem(problemId: Int): TaggedProblem {
        delay(300)
        return mFakeNetWorkDataProvider.getProblem(problemId)
    }

    override suspend fun getSolvedProblems(userId: String): Problems {
        delay(300)
        //solvedby 붙여서 검색해야함
        return mFakeNetWorkDataProvider.getSolvedAlgorithms(userId)
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        delay(300)
        //부분 problem id or problem id
        return mFakeNetWorkDataProvider.getSearchProblemList(problemId)
    }

    override suspend fun getUserInfo(userId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getAutoSearchObject(keyword: String): AutoKeywordObject {
        TODO("Not yet implemented")
    }

    override suspend fun getUserStats(userId: String): List<Stats> {
        delay(300)
        return mFakeNetWorkDataProvider.getStatsList()
    }

    override suspend fun getUnSolvedProblems(solvedacToken: String): List<ProblemStatus> {
        delay(300)
        return mFakeNetWorkDataProvider.getProblemStatsList()
    }

}