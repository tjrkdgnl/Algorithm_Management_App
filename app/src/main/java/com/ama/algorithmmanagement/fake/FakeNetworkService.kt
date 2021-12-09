package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.BaseNetworkService
import kotlinx.coroutines.delay

class FakeNetworkService(private val mFakeNetWorkDataProvider: FakeNetWorkDataProvider)
    : BaseNetworkService {

    override suspend fun getSolvedProblems(userId: String): SolvedAlgorithms {
        delay(1000)

        return mFakeNetWorkDataProvider.getSolvedAlgorithms()
    }
}