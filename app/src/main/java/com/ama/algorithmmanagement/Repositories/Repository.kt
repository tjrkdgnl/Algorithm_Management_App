package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.KAPIGenerator
import com.ama.algorithmmanagement.Network.KAPIService
import kotlinx.coroutines.*
import timber.log.Timber

class Repository() {

    private val networkService = object : NetworkService() {
        override suspend fun getSolvedProblems(
            userId: String
        ): SolvedAlgorithms {
            return KAPIGenerator.getInstance().getSolvedProblemList(userId)
        }
    }


    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms {
        return networkService.getSolvedProblems(userId)
    }
}