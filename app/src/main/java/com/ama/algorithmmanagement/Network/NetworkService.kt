package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.Model.SolvedAlgorithms

abstract class NetworkService {

    abstract suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

}