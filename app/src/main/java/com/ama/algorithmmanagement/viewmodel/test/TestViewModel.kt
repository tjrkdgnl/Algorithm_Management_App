package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.DateInfoObject
import com.ama.algorithmmanagement.Model.UserInfo
import kotlinx.coroutines.launch
import java.lang.Exception

class TestViewModel(private var repository: BaseRepository) : ViewModel() {
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

        viewModelScope.launch {
            try {
                repository.setUserInfo(_userId.value!!, _pwd.value!!)
            } catch (e: Exception) {

            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                repository.getUserInfo()
            } catch (e: Exception) {

            }
        }
    }

    fun checkUserInfo(userId: String, password: String) {
        viewModelScope.launch {
            repository.checkUserInfo(userId, password)
        }
    }

    fun setDateInfo() {
        val date = repository.setDateInfo()
    }

    fun getDateInfoObejct() {
        _dateInfoObejct.value = repository.getDateInfoObject()
    }

}