package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestCommentViewModel(private var repository: BaseRepository) {


    fun setComment(problemId: Int, comment: String) {
        repository.setComment(problemId, comment).onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun getCommentObject(problemId: Int) {

    }

    fun setChildComment(commentId: String?, comment: String) {
        repository.setChildComment(commentId, comment).onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun getChildCommentObject(commentId: String?) {

    }
}