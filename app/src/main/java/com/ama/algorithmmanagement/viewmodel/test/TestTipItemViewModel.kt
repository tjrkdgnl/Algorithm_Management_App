package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.base.BaseRepository
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.launch
import timber.log.Timber

class TestTipItemViewModel(
    private val mRepository: BaseRepository,
    private var lifecycleOwner: LifecycleOwner?
) : ViewModel() {

    val isShow = MutableLiveData<Boolean>(null)
    val tipComment = MutableLiveData<String>(null)
    val combineWith = combineWith(isShow, tipComment) { show, tip -> show != null && tip != null }
    private var tipProblemInfo: TipProblemInfo? = null

    init {
        lifecycleOwner?.let { owner ->
            combineWith.observe(owner, {})
        }
    }

    fun saveTippingProblem() {
        viewModelScope.launch {
            try {
                combineWith.value?.let { result ->
                    if (result) {
                        Timber.e(result.toString())
                        tipProblemInfo?.problem?.let { problem ->
                            val set = mRepository.modifyTippingProblem(
                                problem.problemId,
                                isShow.value!!,
                                tipComment.value!!
                            )

                            Timber.e(set.toString())
                        }
                    } else {
                        Timber.e("false")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun setTipProblemInfo(problem: TipProblemInfo?) {
        tipProblemInfo = problem
    }

    fun isShowTrue() {
        isShow.value = true
    }

    fun isShowFalse() {
        isShow.value = false
    }

    override fun onCleared() {
        super.onCleared()
        combineWith.removeObserver {}
        lifecycleOwner = null
    }
}