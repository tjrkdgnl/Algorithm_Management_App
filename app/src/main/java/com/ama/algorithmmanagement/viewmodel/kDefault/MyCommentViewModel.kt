package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.model.CommentObject
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class MyCommentViewModel(private var mRepository: BaseRepository) :ViewModel() {

    val myCommentList = ObservableArrayList<CommentInfo>()
    var problemId = MutableLiveData<String>()

    init {
        setUserInfo()
        getMyCommentList()
    }


    fun setUserInfo() {
        viewModelScope.launch {
            mRepository.setUserInfo("hongdroid94", "1234")
        }
    }

    private fun getMyCommentList() {
        viewModelScope.launch {
            try {
                val commentObj = mRepository.getCommentObject(1111)
                commentObj?.let {
                    myCommentList.addAll(commentObj.commentList)
                    Timber.e(commentObj.toString())
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}