package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KDefaultMainViewModel : ViewModel() {

    val moveToCallAPIAct = MutableLiveData<Boolean>(false)
    val moveToSolvedAct = MutableLiveData<Boolean>(false)
    val moveToLoginAct = MutableLiveData<Boolean>(false)

    fun moveToCallAPIActivity() {
        moveToCallAPIAct.value = true;
    }

    fun moveToSolvedActivity() {
        moveToSolvedAct.value = true;
    }
    fun moveToLoginAct() {
        moveToLoginAct.value = true;
    }

}