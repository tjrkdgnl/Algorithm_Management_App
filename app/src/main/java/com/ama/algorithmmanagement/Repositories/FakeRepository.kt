package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.NetworkService
import kotlinx.coroutines.delay

class FakeRepository : BaseRepository {
    override val networkService: NetworkService = object : NetworkService() {
        override suspend fun getSolvedProblems(
            userId: String
        ): SolvedAlgorithms {
            //fake network delay
            delay(1000)
            return DataProvider.getSolvedAlgorithms()
        }
    }


    override suspend fun getSolvedProblems(userId: String): SolvedAlgorithms {
        return networkService.getSolvedProblems(userId)
    }
}