package com.ama.algorithmmanagement.presentation.tryhistory

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.data.model.IdeaInfo
import com.ama.algorithmmanagement.data.model.IdeaInfos
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * author  : hongdroid94
 * summary : 나의 아이디어 작성 뷰 모델
 */
class MyIdeaInfoViewModel(
    private val mRepository: BaseRepository) : ViewModel() {

    var ideaList = ObservableArrayList<IdeaInfo>()
    var problemId = MutableLiveData<Int?>()
    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    val ideaInfos: LiveData<IdeaInfos?> = liveData {
        problemId.value?.let { problemVal ->
            mRepository.getIdeaInfos(problemVal).collect {
                emit(it)
            }
        }
    }

    fun setProblemId(_problemId: Int?) {
        problemId.value = _problemId
    }

    fun saveIdeaInfo(url: String?, ideaComment: String?) {
        viewModelScope.launch {
            problemId.value?.let { mRepository.setIdeaInfo(url, ideaComment, it) }
        }
    }
}