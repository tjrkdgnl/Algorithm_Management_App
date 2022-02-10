package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.IdeaInfo
import com.ama.algorithmmanagement.model.IdeaInfos
import kotlinx.coroutines.flow.collect

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

    val ideaInfos: LiveData<IdeaInfos?> = liveData {
        mRepository.getIdeaInfos(1111).collect {
            emit(it)
        }
    }

    init {
        sharedPref.setUserId("testID") // todo : 임시..
//        problemId.observe(mLifecycleOwner!!, { id ->
//            getMyIdeaList(id)
//        })
    }

//    fun saveIdeaInfo() {
//        viewModelScope.launch {
////            check.value?.let {
////                if (it) {
//            mRepository.setIdeaInfo("test_url", "comment_test_value", 1111)
////                }
////            }
//        }
//    }

    fun setProblemId(_problemId: Int?) {
        problemId.value = _problemId!!
    }

}