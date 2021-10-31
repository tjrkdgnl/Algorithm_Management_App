package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.KProblemsOfClass
import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface KAPIService {

    @GET("problem/class")
    suspend fun getProblemsOfClass(): List<KProblemsOfClass>

    @GET("search/problem")
    suspend fun getSolvedProblemList(@Query("query") userId:String) :SolvedAlgorithms
}