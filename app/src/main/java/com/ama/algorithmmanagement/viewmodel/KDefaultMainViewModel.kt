package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KDefaultMainViewModel : ViewModel() {

    val moveToCallAPIAct = MutableLiveData<Boolean>(false)

    fun moveToCallAPIActivity() {
        moveToCallAPIAct.value = true;
    }

}