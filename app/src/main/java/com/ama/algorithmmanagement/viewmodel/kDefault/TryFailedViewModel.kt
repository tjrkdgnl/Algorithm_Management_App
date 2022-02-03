package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber

class TryFailedViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val tryFailedList = ObservableArrayList<TaggedProblem>()
    val isGetSolvedAcToken = MutableLiveData<Boolean>()
    val solvedacToken = MutableLiveData<String>()


    init {
        setUserInfo()
//        getTryFailedProblem()
    }

    fun setUserInfo() {
        viewModelScope.launch {
            mRepository.setUserInfo("hongdroid94", "1234")
        }
    }

    fun setSolvedacToken(token: String?) {
        solvedacToken.value = token
    }

    fun getTryFailedProblem() {
        viewModelScope.launch {
            try {
                val lstUnSolvedProblem = mRepository.getUnSolvedProblems(solvedacToken.value) // List<ProblemStatus>
                for (i in lstUnSolvedProblem.indices) {
                    tryFailedList.add(mRepository.getProblem(lstUnSolvedProblem[i].id))
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}