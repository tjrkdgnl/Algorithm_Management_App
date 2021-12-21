package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.CommentInfo
import com.ama.algorithmmanagement.Model.CommentObject
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
        val comment = mRepository.setComment(mProblemId.value!!, mComment.value!!)
        commentInfoList.add(comment)
    }


}