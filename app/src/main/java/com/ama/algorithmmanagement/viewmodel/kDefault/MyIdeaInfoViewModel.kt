package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * author  : hongdroid94
 * summary : 나의 아이디어 작성 뷰 모델
 */
class MyIdeaInfoViewModel(private val mRepository: BaseRepository) :ViewModel() {

    var ideaList = ObservableArrayList<IdeaInfo>()

    init {
        getMyIdeaList()
    }

    private fun getMyIdeaList() {
        viewModelScope.launch {
            try {
                val lstTippingProblem = mRepository.getTippingProblem()?.problemInfoList
                if (lstTippingProblem != null) {
//                    for (i in lstTippingProblem.indices) {
//                        // todo - 넘겨줘야하는 인자가 Flow 활용 문이 있는거같아서 강휘에게 질문 필요
//                        mRepository.getIdeaInfos(lstTippingProblem[i].problem!!.problemId).collect {
//                            emit(it)
//                        }
//                    }
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun saveIdeaInfo() {
        viewModelScope.launch {
//            check.value?.let {
//                if (it) {
                    mRepository.setIdeaInfo("test_url", "comment_test_value", 1111)
//                }
//            }
        }
    }

}