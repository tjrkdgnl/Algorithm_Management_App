package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingViewModel(private val mRepository: BaseRepository) :ViewModel() {

    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    init {

    }

    fun setLogout() {
        setDeleteAutoLogin()
        setDeleteUserId()
    }

    private fun setDeleteUserId() {
        sharedPref.deleteUserId()
    }

    fun isAutoLoginStatus() : Boolean {
        return sharedPref.getAutoLoginCheck()
    }

    fun setDeleteAutoLogin() {
        sharedPref.deleteAutoLoginCheck()
    }

    fun setAutoLoginStatus(isAuto: Boolean) {
        sharedPref.setAutoLoginCheck(isAuto)
    }

}