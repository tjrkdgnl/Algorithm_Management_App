package com.ama.algorithmmanagement.network

import com.ama.algorithmmanagement.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KAPIService {

    @GET("problem/class")
    suspend fun getProblemsOfClass(): List<KProblemsOfClass>

    @GET("search/problem")
    suspend fun getSearchProblemList(@Query("query") problemId: Int): Problems

    @GET("search/problem")
    suspend fun getSolvedProblems(
        @Query("query") query: String,
        @Query("direction") direction: String? = null,
        @Query("page") page: Int = 1,
        @Query("sort") sort: String? = null
    ): Problems

    @GET("account/verify_credentials")
    suspend fun getUserCredentials(): BOJUser

    @GET("user/problem_stats")
    fun getUserStatsInfo(@Query("handle") userId: String): Call<List<Stats>>

    @GET("user/show")
    suspend fun getUserInfo(@Query("handle") userId: String): User

    @GET("problem/show")
    suspend fun getProblem(@Query("problemId") problemId: Int): TaggedProblem

    @GET("search/suggestion")
    suspend fun getAutoKeyword(@Query("query") keyword:String) : AutoKeywordObject

}