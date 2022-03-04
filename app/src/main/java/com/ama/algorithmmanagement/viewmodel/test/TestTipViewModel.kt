package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.TipProblemInfo
import kotlinx.coroutines.launch
import timber.log.Timber


class TestTipViewModel(
    private var mRepository: BaseRepository,
) : ViewModel() {
    val tipProblems = ObservableArrayList<TipProblemInfo>()

    init {
        checkProblems()
    }

    fun checkProblems() {
//        if (!tipProblems.isEmpty()) return

        viewModelScope.launch {
            try {
                val tipProblemObject = mRepository.getNotTippingProblem()

                Timber.e(tipProblemObject.toString())

                if (tipProblemObject == null) {
                    mRepository.getSolvedProblems().problemList?.let { solvedList ->
                        val initProblems = mRepository.initTipProblems(solvedList)

                        initProblems?.let {
                            tipProblems.addAll(it.problemInfoList)
                        }
                    }
                } else {
                    tipProblems.addAll(tipProblemObject.problemInfoList)
                    Timber.e(tipProblems.toString())
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}