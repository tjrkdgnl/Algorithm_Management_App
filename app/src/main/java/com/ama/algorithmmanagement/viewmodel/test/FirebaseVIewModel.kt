package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.launch
import timber.log.Timber

class FirebaseVIewModel(private val repository: BaseRepository) : ViewModel() {
    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val fcmToken = MutableLiveData<String>()
    val checkUserInfo = combineWith(userId, password) { id, pwd -> id != null && pwd != null }

    fun saveUserInfo() {
        viewModelScope.launch {
            checkUserInfo.value?.let {
                if (it) {
                    val result = repository.setUserInfo(userId.value!!, password.value!!)

                    Timber.e(result.toString())
                } else {
                    Timber.e("id 또는 pwd를 다시 확인해주세요.")
                }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val userInfo = repository.getUserInfo()

                Timber.e(userInfo.toString())

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}