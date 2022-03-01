package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.CommentInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class MyCommentViewModel(
    private var mRepository: BaseRepository) : ViewModel() {

    val myCommentList = ObservableArrayList<CommentInfo>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    private fun getMyCommentList(_problemId: Int) {
        viewModelScope.launch {
            try {
                val commentObj = mRepository.getCommentObject(_problemId)
                val curUserId = sharedPref.getUserId()
                val curUserInfo = mRepository.getUserInfo(curUserId.toString())

                commentObj?.let {
                    for (i in commentObj.commentList.indices) {
                        if (commentObj.commentList[i].userId == curUserInfo?.userId ?: "") {
                            myCommentList.add(commentObj.commentList[i])
                            Timber.e(commentObj.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun setProblemId(_problemId: Int?) {
        _problemId?.let { getMyCommentList(it) }
    }
}