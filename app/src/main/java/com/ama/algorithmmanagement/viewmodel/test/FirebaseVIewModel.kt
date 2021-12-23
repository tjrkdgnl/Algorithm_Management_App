package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.launch
import timber.log.Timber

class FirebaseVIewModel(
    private val repository: BaseRepository,
    private val lifecycleOwner: LifecycleOwner
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val fcmToken = MutableLiveData<String>()
    val checkUserInfo = combineWith(userId, password) { id, pwd -> id != null && pwd != null }
    private val userInfoObserver = Observer<Boolean> { }

    init {
        checkUserInfo.observe(lifecycleOwner, userInfoObserver)
    }

    fun saveUserInfo() {
        viewModelScope.launch {
            checkUserInfo.value?.let {
                if (it) {
                    repository.setUserInfo(userId.value!!, password.value!!)

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

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        checkUserInfo.removeObserver(userInfoObserver)
    }
}