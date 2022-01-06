package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.CommentInfo
import com.ama.algorithmmanagement.Model.CommentObject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


class TestCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {

    val commentInfoList = ObservableArrayList<CommentInfo>()
    var problemId = MutableLiveData<String>()

    //two-way binding으로 사용
    val comment = MutableLiveData<String>()

    var mCommentObject: LiveData<CommentObject?> = liveData {
        mRepository.getCommentObject(1111).collect {
            emit(it)
        }

    }

    fun initCommentObject(problemId: Int) {
        viewModelScope.launch {
            this@TestCommentViewModel.problemId.value = problemId.toString()
        }
    }

    fun saveComment() {
        viewModelScope.launch {
            mRepository.setComment(problemId.value!!.toInt(), comment.value!!)
        }
    }
}