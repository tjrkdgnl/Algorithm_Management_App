package com.ama.algorithmmanagement.Model

data class TippingProblems(
    val count: Int,
    val userId: String,
    val problemList: List<TipProblem>
) {

}