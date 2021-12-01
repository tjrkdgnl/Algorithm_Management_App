package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.SolvedAlgorithms

interface BaseRepository {

    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

}