package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.ChildCommentInfo
import com.ama.algorithmmanagement.model.ChildCommentObject
import kotlinx.coroutines.launch
import timber.log.Timber

class MyChildCommentViewModel(private var mRepository: BaseRepository, mLifecycleOwner: LifecycleOwner?) : ViewModel() {

    private val _childCommentList = MutableLiveData<ChildCommentObject?>()
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()
    var selectCommentId = MutableLiveData<String>()
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {
        selectCommentId.observe(mLifecycleOwner!!,{
            getChildCommentListLoading(it)
        })
    }

    private fun getChildCommentListLoading(commentId : String) {
        viewModelScope.launch {
            try {
                _childCommentList.value = null
                _childCommentList.value = mRepository.getChildCommentObject(commentId)
                childCommentInfoList.clear()
                _childCommentList.value?.let { obj ->
                    for (i in obj.commentChildList.indices) {
                        if (obj.commentChildList[i].userId == mRepository.getUserInfo(sharedPref.getUserId().toString())!!.userId) {
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

}