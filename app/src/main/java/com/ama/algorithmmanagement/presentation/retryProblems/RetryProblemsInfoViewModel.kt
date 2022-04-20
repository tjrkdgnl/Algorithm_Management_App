package com.ama.algorithmmanagement.presentation.retryProblems;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
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

    // 다시풀어볼 문제 불러오기(파베에서 노팁, 팁 문제를 합쳐서 세팅)
    private fun loadRetryProblems() {
        viewModelScope.launch {
            val tipProblems = mutableListOf<TipProblemInfo>()
            Timber.e("e1234")
            repository.getTippingProblem()?.let { tipPb ->
                Timber.e("e1234")
                tipProblems.addAll(tipPb.problemInfoList)
                _retryProblems.value = tipProblems
            }
        }
    }

    init {
        loadRetryProblems()
    }
}