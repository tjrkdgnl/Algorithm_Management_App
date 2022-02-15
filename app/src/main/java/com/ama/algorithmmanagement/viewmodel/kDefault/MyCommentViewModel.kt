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
    private var mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner?
) : ViewModel() {

    val myCommentList = ObservableArrayList<CommentInfo>()
    var problemId = MutableLiveData<Int>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        problemId.observe(mLifecycleOwner!!, { id ->
//            setMyCommentList()
            getMyCommentList(id)
        })
    }

    private fun setMyCommentList() {
        // todo - sample insert
        viewModelScope.launch {
            try {
                mRepository.setComment(problemId.value!!.toInt(), "구슬꿰기 문제 어렵네요")
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    private fun getMyCommentList(_problemId: Int) {
        viewModelScope.launch {
            try {
                val commentObj = mRepository.getCommentObject(_problemId)
                for (i in commentObj!!.commentList.indices) {
                    if (commentObj.commentList[i].userId == mRepository.getUserInfo(sharedPref.getUserId().toString())!!.userId) {
                        myCommentList.add(commentObj.commentList[i])
                        Timber.e(commentObj.toString())
                    }
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun setProblemId(_problemId: Int?) {
        problemId.value = _problemId!!
    }
}