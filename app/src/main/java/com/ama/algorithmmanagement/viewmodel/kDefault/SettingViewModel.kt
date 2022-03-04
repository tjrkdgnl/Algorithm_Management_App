package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import timber.log.Timber

class SettingViewModel(private val mRepository: BaseRepository) :ViewModel() {
    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    // 자동 로그인 상태 값
    private val _isAutoLogin = MutableLiveData(false)
    val isAutoLogin: LiveData<Boolean>
        get() = _isAutoLogin

    // 로그아웃 동작
    private val _isSelectedLogout = MutableLiveData(false)
    val isSelectedLogout: LiveData<Boolean>
        get() = _isSelectedLogout

    init {
        Timber.d("viewmodel init")
        // load last auto login option
        toggleAutoLoginCheck(sharedPref.getAutoLoginCheck())
    }

    fun setLogout() {
        // 로그아웃
        sharedPref.deleteAutoLoginCheck()
        sharedPref.deleteUserId()
        _isSelectedLogout.value = true
    }

    fun toggleAutoLoginCheck(isChecked : Boolean) {
        // 자동 로그인 토글
        sharedPref.setAutoLoginCheck(isChecked)
        _isAutoLogin.value = isChecked
    }
}