package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.Problem
import com.ama.algorithmmanagement.Model.SolvedAlgorithms

class FakeNetWorkDataProvider {

    fun getSolvedAlgorithms(): SolvedAlgorithms {
        return SolvedAlgorithms(
            3, mutableListOf(
                Problem(1111, "구슬치기", true, false, 0, 3, 76500, true, 2.5),
                Problem(2311, "구슬던지기", true, false, 345, 4, 4341, false, 1.5),
                Problem(4361, "구슬박치기", false, true, 1000, 1, 400, false, 4.5)
            )
        )
    }


}