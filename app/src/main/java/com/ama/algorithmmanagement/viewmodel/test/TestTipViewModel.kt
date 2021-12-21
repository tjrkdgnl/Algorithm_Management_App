package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.Model.TipProblem
import com.ama.algorithmmanagement.Model.TippingProblemObject
import timber.log.Timber


class TestTipViewModel(private var repository: BaseRepository) {
    val tipProblems = ObservableArrayList<TipProblem>()
    var tipProbleObject: TippingProblemObject? = null

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ) {
        val tip = repository.setTippingProblem(problem, isShow, tipComment)
        tipProblems.add(tip)
    }

    fun initTipProblemObject() {
        tipProbleObject = repository.getTippingProblem()
    }

}