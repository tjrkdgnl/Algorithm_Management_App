package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.base.BaseRepository
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber

class TryFailedViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val tryFailedList = ObservableArrayList<TaggedProblem>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        sharedPref.setUserId("seungho0510") // todo : 임시..

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

    fun setSolvedacToken(token: String) {
        sharedPref.setSolvedacToken(token)
    }
}