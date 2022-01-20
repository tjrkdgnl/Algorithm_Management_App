package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.model.ProblemStatus
import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.model.Stats
import com.ama.algorithmmanagement.model.TaggedProblem

interface BaseNetworkService {

    suspend fun getProblem(problemId:Int) : TaggedProblem

    suspend fun getSolvedProblems(userId: String): Problems

    suspend fun getUserStats(userId:String) : List<Stats>

    suspend fun getUnSolvedProblems(solvedacToken:String) : List<ProblemStatus>

    suspend fun getSearchProblemList(problemId:Int): Problems


}