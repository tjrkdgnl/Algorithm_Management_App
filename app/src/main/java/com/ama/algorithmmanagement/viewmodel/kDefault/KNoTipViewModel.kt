package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.KProblemsOfClass
import com.ama.algorithmmanagement.Model.Problems
import com.ama.algorithmmanagement.Model.TaggedProblem
import com.ama.algorithmmanagement.Network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class KNoTipViewModel(private val mRepository: BaseRepository) :ViewModel() {

    private val _classList = MutableLiveData<List<KProblemsOfClass>>()
    val classList: LiveData<List<KProblemsOfClass>> get() = _classList

    private val _solvedAlgorithms = MutableLiveData<Problems>()
    val solvedList: LiveData<MutableList<TaggedProblem>> = Transformations.map(_solvedAlgorithms) {
        it.problemList
    }

    init {
        // set save user account
        mRepository.setUserInfo("hongdroid94@gmail.com", "password")
        getSolvedProblems()
    }

    // request api using coroutine
    private fun initList() {
        viewModelScope.launch {
            try {
                _classList.value = KAPIGenerator.getInstance().getProblemsOfClass()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    private fun getSolvedProblems() {
        viewModelScope.launch {
            try {
                _solvedAlgorithms.value = mRepository.getSolvedProblems()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }


}