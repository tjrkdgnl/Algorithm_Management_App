package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber

class TryFailedViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val tryFailedList = ObservableArrayList<TaggedProblem>()

    init {
        getTryFailedProblem()
    }

    private fun getTryFailedProblem() {
        viewModelScope.launch {
            try {
                val lstUnSolvedProblem = mRepository.getUnSolvedProblems("cookie") // List<ProblemStatus>
                for (i in lstUnSolvedProblem.indices) {
                    tryFailedList.add(mRepository.getProblem(lstUnSolvedProblem[i].id))
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}