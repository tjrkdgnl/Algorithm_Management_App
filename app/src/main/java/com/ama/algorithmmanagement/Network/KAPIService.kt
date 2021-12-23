package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KAPIService {

    @GET("problem/class")
    suspend fun getProblemsOfClass() : List<KProblemsOfClass>

    @GET("search/problem")
    suspend fun getSearchProblemList(@Query("query") param:String) : Problems

    @GET("search/problem")
    suspend fun getSolvedProblems(@Query("query") param:String) : Problems

    @GET("account/verify_credentials")
    suspend fun getBOJUserInfo() : BOJUser

    @GET("user/problem_stats")
    suspend fun getUserStatsInfo(@Query("handle") userId:String) : Call<List<Stats>>

    @GET("problem/show")
    suspend fun getProblem(@Query("problemId")problemId:Int) : TaggedProblem

}