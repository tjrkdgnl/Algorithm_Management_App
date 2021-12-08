package com.ama.algorithmmanagement.Model

data class TippingProblemObject(
    val count: Int,
    val userId: String,
    val problemList: MutableList<TipProblem>
) {

}