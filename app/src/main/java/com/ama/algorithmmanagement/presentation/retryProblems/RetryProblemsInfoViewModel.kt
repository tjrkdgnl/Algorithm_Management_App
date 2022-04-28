package com.ama.algorithmmanagement.presentation.retryProblems;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.data.firebase.SortEnum
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.utils.sort
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
class RetryProblemsInfoViewModel(private val repository: BaseRepository) : ViewModel() {
    // 다시 풀어볼 문제
    private val _retryProblems = MutableLiveData<MutableList<TipProblemInfo>>()
    val retryProblems: LiveData<MutableList<TipProblemInfo>>
        get() = _retryProblems

    // 정렬상태
    private val _problemSortState = MutableLiveData<SortEnum>(SortEnum.PAST)
    val problemSortState: LiveData<SortEnum>
        get() = _problemSortState

    // 정렬상태에 맞게 문제 세팅
    private fun initRetryProblems() {
        problemSortState.value?.let{
            loadRetryProblems(it)
        }
    }

    // enum 타입에 맞게 문제리스트 재정렬
    fun loadRetryProblems(sortEnum: SortEnum) {
        viewModelScope.launch {
            val problems = repository.getAllTipProblems()
            problems?.sort(sortEnum)
            _retryProblems.value = problems?.problemInfoList
        }
    }

    init {
        initRetryProblems()
    }
}