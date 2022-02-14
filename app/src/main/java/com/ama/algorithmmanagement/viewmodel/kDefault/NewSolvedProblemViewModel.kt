package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TippingProblemObject
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : NewSolvedProblemViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class NewSolvedProblemViewModel(private val mRepository:BaseRepository) : ViewModel() {

    // 현재 작성하고있는 팁 페이지
    private val _writeTipCurrentPage = MutableLiveData<Int>(0)
    val writeTipCurrentPage:LiveData<Int>
        get() = _writeTipCurrentPage

    // 작성하지 않은 팁페이지 (noTipProblem)
    private val _noTipProblem = MutableLiveData<TippingProblemObject>()
    val noTipProblem:LiveData<TippingProblemObject>
        get() = _noTipProblem

    private fun loadTippingProblem(){
        viewModelScope.launch {
            _noTipProblem.value = mRepository.getNotTippingProblem()
            Timber.e("notip problem ${_noTipProblem.value}")
        }
    }
    fun skipTipPage(){
        _writeTipCurrentPage.value = _writeTipCurrentPage.value?.plus(1)
    }
    init{
        loadTippingProblem()
    }


}