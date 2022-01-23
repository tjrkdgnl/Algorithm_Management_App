package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.model.CommentObject
import kotlinx.coroutines.launch
import timber.log.Timber

class MyCommentViewModel(private val mRepository: BaseRepository) :ViewModel() {

    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    private val _myCommentAlgorithms = MutableLiveData<CommentObject>()
    val myCommentList: LiveData<MutableList<CommentInfo>> = Transformations.map(_myCommentAlgorithms) {
        it.commentList
    }

    init {
        saveUserId()
        setTestSaveComment()
        getMyCommentList()
    }

    fun saveUserId() {
        sharedPref.setUserId("hongdroid94")
    }

    private fun setTestSaveComment() {
        viewModelScope.launch {
            // todo - 파이어베이스 insert 행위 같은데 이 것도 정상작동 안되는 듯 함..
            mRepository.setComment(1111, "TEST_HONGCHUL_COMMENT")
        }
    }

    private fun getMyCommentList() {
        viewModelScope.launch {
            try {
//                val lstTippingProblem = mRepository.getTippingProblem()?.problemInfoList
//                if (lstTippingProblem != null) {
//                    for (i in lstTippingProblem.indices) {
//                        _myCommentAlgorithms.value = mRepository.getCommentObject(lstTippingProblem[i].problem!!.problemId)
//                    }
//                }
                _myCommentAlgorithms.value = mRepository.getCommentObject(1111)

                // todo - firebase database load 해오는 권한이 강휘말고 모두에게 있는건지 확인..! 데이터를 정상적으로 못 가져오는 것 같다..
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
}