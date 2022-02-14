package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KDefaultMainViewModel : ViewModel() {

    val moveToCallAPIAct = MutableLiveData<Boolean>(false)
    val moveToSolvedAct = MutableLiveData<Boolean>(false)
    val moveToLoginAct = MutableLiveData<Boolean>(false)
    val moveToFirebaseTestAct = MutableLiveData<Boolean>(false)
    val moveToSharedTestAct = MutableLiveData<Boolean>(false)
    val moveToIdeaTestAct = MutableLiveData<Boolean>(false)
    val moveToDateTestAct = MutableLiveData<Boolean>(false)
    val moveToCommentTestAct = MutableLiveData<Boolean>(false)
    val moveToSearchTestAct = MutableLiveData<Boolean>(false)



    fun moveToCallAPIActivity() {
        moveToCallAPIAct.value = true;
    }

    fun moveToSolvedActivity() {
        moveToSolvedAct.value = true;
    }
    fun moveToLoginAct() {
        moveToLoginAct.value = true;
    }
    fun moveToFirebaseTestAct() {
        moveToFirebaseTestAct.value = true;
    }
    fun moveToSharedTestAct() {
        moveToSharedTestAct.value = true;
    }
    fun moveToIdeaTestAct() {
        moveToIdeaTestAct.value = true;
    }
    fun moveToDateTestAct() {
        moveToDateTestAct.value = true;
    }
    fun moveToCommentTestAct() {
        moveToCommentTestAct.value = true;
    }
    fun moveToSearchTestAct() {
        moveToSearchTestAct.value = true;
    }

}