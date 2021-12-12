package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.ProblemStatus
import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Model.Stats

interface BaseNetworkService {

    suspend fun getSolvedProblems(userId: String): Problems

    suspend fun getUserStats(userId:String) : List<Stats>

    suspend fun getBOJUserInfo() : List<ProblemStatus>

    suspend fun getSearchProblemList(problemId:String): Problems


}