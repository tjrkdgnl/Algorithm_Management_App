package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import timber.log.Timber


class TestViewModel(private var repository: BaseRepository) {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String> = _pwd

    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    private val _dateInfoObejct = MutableLiveData<DateInfoObject?>()
    val dateInfoObejct: LiveData<DateInfoObject?> = _dateInfoObejct


    fun setUserInfo() {
        this._userId.value = "skjh0818"
        this._pwd.value = "myPwd"
        repository.setUserInfo(_userId.value!!, _pwd.value!!)
    }

    fun getUserInfo(): UserInfo? {
        return repository.getuserInfo()
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        return repository.checkUserInfo(userId, password)
    }

    fun setDateInfo() {
        repository.setDateInfo().onSuccess {

        }.onFailure {
            Timber.e(it)
        }
    }

    fun getDateInfoObejct() {
        _dateInfoObejct.value = repository.getDateInfoObject()
    }

}