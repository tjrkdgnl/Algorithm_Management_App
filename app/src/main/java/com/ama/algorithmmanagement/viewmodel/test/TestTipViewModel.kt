package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestTipViewModel(private var repository: BaseRepository) {

    var tippingProblemObject: TippingProblemObject? = null

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ) {
        repository.setTippingProblem(problem, isShow, tipComment).onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun initTippingProblem() {
        tippingProblemObject = repository.getTippingProblem()

    }

}