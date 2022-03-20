package com.ama.algorithmmanagement.presentation.tryfailed

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber

class TryFailedViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val tryFailedList = ObservableArrayList<TaggedProblem>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        val solvedacToken = sharedPref.getSolvedacToken() // todo : 임시.. 차후 SharedPref가 Repository에서 관리 될 때 수정해야 함.
        getTryFailedProblem(solvedacToken)
    }

    private fun getTryFailedProblem(token: String?) {
        viewModelScope.launch {
            try {
                val lstUnSolvedProblem = mRepository.getUnSolvedProblems(token) // List<ProblemStatus>
                for (i in lstUnSolvedProblem.indices) {
                    tryFailedList.add(mRepository.getProblem(lstUnSolvedProblem[i].id))
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}