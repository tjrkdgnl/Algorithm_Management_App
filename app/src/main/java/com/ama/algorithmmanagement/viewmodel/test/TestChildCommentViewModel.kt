package com.ama.algorithmmanagement.viewmodel.test

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.ChildCommentInfo
import kotlinx.coroutines.launch
import timber.log.Timber

class TestChildCommentViewModel(private var mRepository: BaseRepository) : ViewModel() {
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()
    private var mCommentId: String? = null
    private var mProblemId: Int? =null
    val mComment = MutableLiveData<String>()

    fun initChildCommentObject(commentId: String) {
        mCommentId = commentId
        getChildCommentObj()
    }

    fun setCommentId(id:String?){
        mCommentId = id
    }

    fun setProblemId(id:Int?){
        mProblemId = id
    }

    fun saveChildComment() {
        viewModelScope.launch {
            try {
                Timber.e("$mCommentId $mComment")

                if(mProblemId !=null && mCommentId !=null){
                    mRepository.setChildComment(mProblemId!!,mCommentId!!, mComment.value!!)
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun getChildCommentObj() {
        viewModelScope.launch {
            try {
                mCommentId?.let { id ->
                    val childCommentObject = mRepository.getChildCommentObject(id)

                    childCommentObject?.let { obj ->
                        childCommentInfoList.addAll(obj.commentChildList)
                    }
                }

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}