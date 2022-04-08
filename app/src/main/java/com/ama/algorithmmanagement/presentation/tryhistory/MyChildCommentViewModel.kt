package com.ama.algorithmmanagement.presentation.tryhistory

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.ChildCommentInfo
import com.ama.algorithmmanagement.domain.entity.ChildCommentObject
import kotlinx.coroutines.launch
import timber.log.Timber

class MyChildCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {

    private val _childCommentList = MutableLiveData<ChildCommentObject?>()
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    private fun getChildCommentListLoading(commentId : String) {
        viewModelScope.launch {
            try {
                _childCommentList.value = null
                _childCommentList.value = mRepository.getChildCommentObject(commentId)
                childCommentInfoList.clear()

                val curUserId = sharedPref.getUserId()
                val curUserInfo = mRepository.getUserInfo(curUserId.toString())
                _childCommentList.value?.let { obj ->
                    for (i in obj.commentChildList.indices) {
                        if (obj.commentChildList[i].userId == curUserInfo?.userId ?: "") {
                            childCommentInfoList.add(obj.commentChildList[i])
                        }
                    }
                    Timber.e(obj.toString())
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun setCommentId(_commentId: String?) {
        _commentId?.let { getChildCommentListLoading(it) }
    }

}