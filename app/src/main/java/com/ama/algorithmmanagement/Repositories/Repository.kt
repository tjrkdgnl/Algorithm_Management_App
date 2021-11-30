package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.KAPIGenerator
import com.ama.algorithmmanagement.Network.NetworkService

class Repository : BaseRepository {

    private val networkService = object : NetworkService() {
        override suspend fun getSolvedProblems(
            userId: String
        ): SolvedAlgorithms {
            return KAPIGenerator.getInstance().getSolvedProblemList(userId)
        }
    }


    override suspend fun getSolvedProblems(userId: String): SolvedAlgorithms {
        return networkService.getSolvedProblems(userId)
    }
}