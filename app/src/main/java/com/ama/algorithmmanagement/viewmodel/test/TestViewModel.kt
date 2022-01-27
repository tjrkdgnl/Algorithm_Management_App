package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.DateObject
import kotlinx.coroutines.launch


class TestViewModel(private var repository: BaseRepository) : ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String> = _pwd

    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    private val _dateInfoObejct = MutableLiveData<DateObject?>()
    val dateObejct: LiveData<DateObject?> = _dateInfoObejct

    var solvedProblemsCount :Int =0

    fun setUserInfo() {
        viewModelScope.launch {
            _userId.value = "skjh0818"
            _pwd.value = "myPwd"
            repository.setUserInfo(_userId.value!!, _pwd.value!!)
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = repository.getUserInfo()
        }
    }

    fun checkUserInfo(userId: String, password: String) {
        viewModelScope.launch {
            repository.checkUserInfo(userId, password)
        }
    }

    fun setDateInfo() {
        viewModelScope.launch {
            val date = repository.setDateInfo(solvedProblemsCount)

        }
    }

    fun getDateInfoObejct() {
        viewModelScope.launch {

        }
    }

}