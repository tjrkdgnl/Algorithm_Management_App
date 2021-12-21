package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.ChildCommentInfo
import com.ama.algorithmmanagement.Model.ChildCommentObject

class TestChildCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {
    var mChildCommentObject: ChildCommentObject? = null
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()

    var mCommentId = MutableLiveData<String>()
    val mComment = MutableLiveData<String>()

    fun initChildCommentObject(commentId: String) {
        mCommentId.value = commentId
        mChildCommentObject = mRepository.getChildCommentObject(commentId)

        mChildCommentObject?.let { childCommentObject ->

            childCommentInfoList.addAll(childCommentObject.commentChildList)
        }
    }

    fun saveChildComment() {
        val comment = mRepository.setChildComment(mCommentId.value!!, mComment.value!!)
        childCommentInfoList.add(comment)
    }

}