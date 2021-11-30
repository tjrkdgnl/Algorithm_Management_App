package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Network.NetworkService

interface BaseRepository {

    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

}