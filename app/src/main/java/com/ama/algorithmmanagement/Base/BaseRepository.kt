package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.NetworkService

interface BaseRepository {

    val networkService: NetworkService

    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

}