package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.utils.combineWith
import timber.log.Timber


class TestCommentViewModel(private var mRepository: BaseRepository) {
    var mCommentObject: CommentObject? = null
    val commentInfoList = ObservableArrayList<CommentInfo>()

    var mProblemId = MutableLiveData<Int>()

    //two-way binding으로 사용
    val mComment = MutableLiveData<String>()

    fun initCommentObject(problemId: Int) {
        mProblemId.value = problemId
        mCommentObject = mRepository.getCommentObject(problemId)

        mCommentObject?.let { commentObj ->
            commentInfoList.addAll(commentObj.commentList)
        }
    }

    fun saveComment() {
//        val checkData =
//            combineWith(mProblemId, mComment) { id, comment -> id != null && comment != null }
//
//        checkData.value?.let { isPossible ->
//            if (isPossible) {
                mRepository.setComment(mProblemId.value!!, mComment.value!!)
                    .onSuccess { commentInfo ->
                        if (!commentInfoList.contains(commentInfo)) {
                            commentInfoList.add(commentInfo)
                        }
                    }.onFailure {
                        Timber.e(it)
                    }
//            }
//        }
    }


}