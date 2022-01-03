package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.IdeaInfo
import com.ama.algorithmmanagement.Model.IdeaInfos
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class IdeaViewModel(
    private var mRepository: BaseRepository,
    private val mLifeCycleOwner: LifecycleOwner?
) :
    ViewModel() {
    var ideaList = ObservableArrayList<IdeaInfo>()
    val comment = MutableLiveData<String>(null)
    val problemId = MutableLiveData<String>(null)
    var url: String? = null
    private val check = combineWith(
        comment,
        problemId
    ) { ment, id -> ment != null && id != null }
    private val observe = Observer<Boolean>() {}

    val ideaInfos: LiveData<IdeaInfos?> = liveData {
        mRepository.getIdeaInfos(1111).collect {
            emit(it)
        }
    }

    init {
        mLifeCycleOwner?.let {
            check.observe(it, observe)
        }
    }

    fun saveIdeaInfo() {
        viewModelScope.launch {
            check.value?.let {
                if (it) {
                    mRepository.setIdeaInfo(url, comment.value, problemId.value!!.toInt())
                }
            }
        }
    }
}