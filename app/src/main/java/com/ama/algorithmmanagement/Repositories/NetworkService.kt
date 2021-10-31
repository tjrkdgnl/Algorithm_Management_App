package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import kotlinx.coroutines.Deferred

abstract class NetworkService {

    abstract suspend fun getSolvedProblems(userId: String) :SolvedAlgorithms

}