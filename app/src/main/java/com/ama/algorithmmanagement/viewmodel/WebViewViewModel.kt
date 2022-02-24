package com.ama.algorithmmanagement.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import timber.log.Timber
import java.util.*

/**
 * @author SeungHo Lee
 * summary : 웹뷰 뷰모델
 */
class WebViewViewModel(repository: BaseRepository) : ViewModel() {
    private val _problemId = MutableLiveData<Int>()
    val problemId: LiveData<Int>
        get() = _problemId

    private val _isFinishActivity = MutableLiveData<Boolean>(false)
    val isFinishActivity:LiveData<Boolean>
        get() = _isFinishActivity

    fun finishActivity(){
        _isFinishActivity.value = !isFinishActivity.value!!
    }

    fun setProblemId(id:Int){
        _problemId.value = id
    }

}