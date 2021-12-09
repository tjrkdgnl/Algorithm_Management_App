package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.SolvedAlgorithms

interface BaseNetworkService {

    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

}