package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.ProblemStatus
import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Model.Stats
import com.ama.algorithmmanagement.Model.TaggedProblem

interface BaseNetworkService {

    suspend fun getProblem(problemId:Int) : TaggedProblem

    suspend fun getSolvedProblems(userId: String): Problems

    suspend fun getUserStats(userId:String) : List<Stats>

    suspend fun getUnSolvedProblems() : List<ProblemStatus>

    suspend fun getSearchProblemList(problemId:Int): Problems


}