package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.model.CommentObject
import com.ama.algorithmmanagement.model.TaggedProblem
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class MyCommentViewModel(
    private var mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner
) : ViewModel() {

    val myCommentList = ObservableArrayList<CommentInfo>()
    var problemId = MutableLiveData<Int>()

    init {
        setUserInfo()
        problemId.observe(mLifecycleOwner, { id ->
            getMyCommentList(id)
        })
    }


    fun setUserInfo() {
        viewModelScope.launch {
            mRepository.setUserInfo("hongdroid94", "1234")
        }
    }

    private fun getMyCommentList(_problemId: Int) {
        viewModelScope.launch {
            try {
                val commentObj = mRepository.getCommentObject(_problemId)
                for (i in commentObj!!.commentList.indices) {
                    if (commentObj.commentList[i].userId.equals(mRepository.getUserInfo())) {
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
        problemId.value = _problemId
    }
}