package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.ChildCommentInfo
import com.ama.algorithmmanagement.Model.ChildCommentObject
import timber.log.Timber

class TestChildCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {
    private var mChildCommentObject: ChildCommentObject? = null
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()


    fun setChildComment(commentId: String, comment: String) {
        mRepository.setChildComment(commentId, comment).onSuccess {
            childCommentInfoList.add(it)
        }.onFailure {
            Timber.e(it)
        }
    }

    fun getChildCommentObject(commentId: String) {
        mChildCommentObject = mRepository.getChildCommentObject(commentId)

        mChildCommentObject?.let { childCommentObject ->
            childCommentInfoList.addAll(childCommentObject.commentChildList)
        }
    }

}