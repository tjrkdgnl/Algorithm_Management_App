package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.Model.TipProblem
import com.ama.algorithmmanagement.Model.TippingProblemObject


class TestTipViewModel(private var repository: BaseRepository) : ViewModel() {
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

    fun getTipProblemObject() {
        tipProbleObject = repository.getTippingProblem()
    }

    fun getNottipProblemObject() :TippingProblemObject? {
       return repository.getNotTippingProblem()
    }

}