package com.ama.algorithmmanagement.domain.base

import com.ama.algorithmmanagement.data.model.*

interface BaseNetworkService {

    suspend fun getProblem(problemId:Int) : TaggedProblem

    suspend fun getSolvedProblems(userId: String): Problems

    suspend fun getUserStats(userId:String) : List<Stats>

    suspend fun getUnSolvedProblems(solvedacToken:String) : List<ProblemStatus>

    suspend fun getSearchProblemList(problemId:Int): Problems

    suspend fun getUserInfo(userId:String) : User


    suspend fun getAutoSearchObject(keyword:String) : AutoKeywordObject

}