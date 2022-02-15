package com.ama.algorithmmanagement.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 문제 상세 보기 뷰모델 (액티비티, 프래그먼트 공용)
 */

class KViewProblemDetailViewModel(
    private val mRepository: BaseRepository
    ) : ViewModel() {

    private val _commentList = MutableLiveData<CommentObject?>()
    val commentInfoList = ObservableArrayList<CommentInfo>()

    private val _childCommentList = MutableLiveData<ChildCommentObject?>()
    val childCommentInfoList = ObservableArrayList<ChildCommentInfo>()

    private val _problemInfo = MutableLiveData<TaggedProblem>()

    var problemId = MutableLiveData<Int>()
    var isGetProblemId = MutableLiveData<Boolean>()

    var selectCommentId = MutableLiveData<String>()
    var selectComment = MutableLiveData<String>()
    var selectCommentUserId = MutableLiveData<String>()
    var selectCommentDate = MutableLiveData<String>()
    var isCommentIdGet = MutableLiveData<Boolean>()

    val problemTitle = MutableLiveData<String>()
    var problemCategory = MutableLiveData<String>()
    var problemTryCount = MutableLiveData<String>()
    var problemLevel = MutableLiveData<String>()
    var problemSuccessCount = MutableLiveData<String>()

    val commentContent = MutableLiveData<String>()
    var isCommentInputSuccess = MutableLiveData<Boolean>()

    val childCommentContent = MutableLiveData<String>()
    var isChildCommentInputSuccess = MutableLiveData<Boolean>()

    var isMoveToBOJWebView = MutableLiveData<Boolean>()
    var isBackCommentFrag = MutableLiveData<Boolean>()

    init {
        isGetProblemId.value = true
        Timber.e("problemId : ${problemId.value}")
    }

    fun getProblemInfo() {
        viewModelScope.launch {
            try {
                Timber.e("problemId : ${problemId.value}")
                _problemInfo.value = mRepository.getProblem(problemId.value!!)
                _problemInfo.value?.let{
                    problemTitle.value = it.titleKo
                    problemCategory.value = it.tags[0].key
                    problemTryCount.value = it.averageTries.toString()
                    problemLevel.value = it.level.toString()
                    problemSuccessCount.value = it.acceptedUserCount.toString()
                }
                Timber.e(_problemInfo.value.toString())

            } catch (e : Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun moveToBojPage() {
        isMoveToBOJWebView.value = true
    }

    fun closeToBojPage() {
        isMoveToBOJWebView.value = false
    }

    fun setComment() {
        viewModelScope.launch {
            try {
                isCommentInputSuccess.value = mRepository.setComment(problemId.value!!, commentContent.value.toString())
                commentContent.value = ""
                getCommentListLoading()
            } catch (e : Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun setChildComment() {
        viewModelScope.launch {
            try {
                isChildCommentInputSuccess.value = mRepository.setChildComment(problemId.value!!, selectCommentId.value!!, childCommentContent.value.toString())
                childCommentContent.value = ""
                getChildCommentListLoading()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun getCommentListLoading() {
        viewModelScope.launch {
            try {
                _commentList.value = null
                _commentList.value = mRepository.getCommentObject(problemId.value!!)
                commentInfoList.clear()
                _commentList.value?.let { obj ->
                    commentInfoList.addAll(obj.commentList)
                    Timber.e(obj.toString())
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun getChildCommentListLoading() {
        viewModelScope.launch {
            try {
                _childCommentList.value = null
                _childCommentList.value = mRepository.getChildCommentObject(selectCommentId.value!!)
                childCommentInfoList.clear()
                _childCommentList.value?.let { obj ->
                    childCommentInfoList.addAll(obj.commentChildList)
                    Timber.e(obj.toString())
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    fun backCommentFrag() {
        isBackCommentFrag.value = true
    }

}