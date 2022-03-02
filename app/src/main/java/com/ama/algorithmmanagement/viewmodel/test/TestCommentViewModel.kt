package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.base.BaseRepository
import com.ama.algorithmmanagement.model.CommentInfo
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


class TestCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {

    val commentInfoList = ObservableArrayList<CommentInfo>()
    var problemId = MutableLiveData<String>()

    //two-way binding으로 사용
    val comment = MutableLiveData<String>()


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

    fun getCommentObj() {
        viewModelScope.launch {
            try {
                problemId.value?.let { id ->
                    val commentObj = mRepository.getCommentObject(id.toInt())

                    commentObj?.let { obj ->
                        commentInfoList.addAll(commentObj.commentList)
                        Timber.e(commentObj.toString())
                    }
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}