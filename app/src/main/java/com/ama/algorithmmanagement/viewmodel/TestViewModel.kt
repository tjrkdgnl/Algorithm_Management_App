package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Model.UserInfo
import com.ama.algorithmmanagement.Repositories.RepositoryLocator


class TestViewModel {
    private val repository = RepositoryLocator.getFakeRepository()

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String> = _pwd

//    private val block: (String?, String?) -> Boolean = { s, s2 -> s != null && s2 != null }


    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    fun setUserInfo() {
        this._userId.value = "skjh0818"
        this._pwd.value = "myPwd"
//        combineWith(userId, pwd, block)
        repository.setUserInfo(_userId.value!!, _pwd.value!!)
    }

    fun getUserInfo(): UserInfo? {
        return repository.getuserInfo()
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        return repository.checkUserInfo(userId, password)
    }

//    fun <T, K, R> combineWith(
//        liveDataSource1: LiveData<T>,
//        liveDataSource2: LiveData<K>,
//        block: (T?, K?) -> R
//    ): LiveData<R> {
//        val result = MediatorLiveData<R>()
//
//        result.addSource(liveDataSource1) {
//            result.value = block(liveDataSource1.value, liveDataSource2.value)
//        }
//
//        result.addSource(liveDataSource2) {
//            result.value = block(liveDataSource1.value, liveDataSource2.value)
//        }
//
//        return result
//    }
}