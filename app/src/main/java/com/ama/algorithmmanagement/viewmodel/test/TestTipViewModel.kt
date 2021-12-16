package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestTipViewModel(private var repository: BaseRepository) {
    val tipProblems = ObservableArrayList<TipProblem>()

    var tipProbleObject :TippingProblemObject? =null

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ) {
        repository.setTippingProblem(problem, isShow, tipComment).onSuccess {
            if (!tipProblems.contains(it)) {
                tipProblems.add(it)
            }
        }.onFailure {
            Timber.e(it)
        }
    }

    fun getTipProblemObject(){
        tipProbleObject = repository.getTippingProblem()
    }

}