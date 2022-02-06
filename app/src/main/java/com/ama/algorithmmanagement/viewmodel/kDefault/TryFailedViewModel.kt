package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber

class TryFailedViewModel(
    private val mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner?
    ) :ViewModel() {

    val tryFailedList = ObservableArrayList<TaggedProblem>()
    val solvedacToken = MutableLiveData<String>()

    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        sharedPref.setUserId("hongdroid94") // todo : 임시..
        solvedacToken.observe(mLifecycleOwner!!, { token ->
            getTryFailedProblem(token)
        })

    }

    fun setSolvedacToken(token: String?) {
        solvedacToken.value = token!!
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