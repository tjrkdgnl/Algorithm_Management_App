package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.KProblemsOfClass
import com.ama.algorithmmanagement.data.model.Problems
import com.ama.algorithmmanagement.data.model.TaggedProblem
import com.ama.algorithmmanagement.data.network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class KAPICallViewModel(private val mRepository: BaseRepository) : ViewModel() {

    private val _classList = MutableLiveData<List<KProblemsOfClass>>()
    val classList: LiveData<List<KProblemsOfClass>>
        get() = _classList

    private val _solvedAlgorithms = MutableLiveData<Problems>()
    val solvedList: LiveData<MutableList<TaggedProblem>> = Transformations.map(_solvedAlgorithms) {
        it.problemList
    }

    init {
        getSolvedProblems()
    }

    //뷰모델에서 코루틴을 사용하여 네트워크 통신을 할 경우
    private fun initList() {
        viewModelScope.launch {
            try {

                _classList.value = KAPIGenerator.getInstance().getProblemsOfClass()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    //레포지토리 패턴을 통해 네트워크 통신 할 결우
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
