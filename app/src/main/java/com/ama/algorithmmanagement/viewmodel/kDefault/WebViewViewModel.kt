package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.base.BaseRepository

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
        _isFinishActivity.value?.let{
            _isFinishActivity.value = !it
        }
    }

    fun setProblemId(id:Int){
        _problemId.value = id
    }

}