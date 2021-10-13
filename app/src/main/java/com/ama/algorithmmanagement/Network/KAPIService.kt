package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.KProblemsOfClass
import retrofit2.http.GET

interface KAPIService {

    @GET("problem/class")
    suspend fun getProblemsOfClass(): List<KProblemsOfClass>
}