package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * author  : hongdroid94
 * summary : 나의 아이디어 작성 뷰 모델
 */
class MyIdeaInfoViewModel(
    private val mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner?
) : ViewModel() {

    var ideaList = ObservableArrayList<IdeaInfo>()
    var problemId = MutableLiveData<Int>()
    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        sharedPref.setUserId("testID") // todo : 임시..
        problemId.observe(mLifecycleOwner!!, { id ->
            getMyIdeaList(id)
        })
    }

    private fun getMyIdeaList(_problemId: Int) {
        viewModelScope.launch {
            try {
                mRepository.getIdeaInfos(1111).collect {
                    Timber.d(it!!.ideaList.toString())
                    ideaList.addAll(it!!.ideaList)
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

    fun setProblemId(_problemId: Int?) {
        problemId.value = _problemId!!
    }

}