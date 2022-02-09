package com.ama.algorithmmanagement.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.fake.FakeSharedPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
<<<<<<< Updated upstream
/**
 * author : manyong Han
 * summary : 스플래시 뷰모델
 */
class KSplashViewModel() : ViewModel() {
=======

class KSplashViewModel(
    private val mRepository: BaseRepository
    ) : ViewModel() {
>>>>>>> Stashed changes
    private val mSharedPrefUtils = AMAApplication.INSTANCE.sharedPrefUtils

    var isUserIdCheck = false
    var isAutoLoginCheck = false

    val isGoToMain = MutableLiveData<Boolean>()

    init {
        getUserLoginInfoCheck()
    }

    private fun getUserLoginInfoCheck() {
        viewModelScope.launch {
            try {
<<<<<<< Updated upstream
                delay(2000) // 스플래시 화면이 안보이고 그냥 넘어가버리는 상황을 방지
                isAutoLoginCheck = mSharedPrefUtils.getAutoLoginCheck()
=======
                delay(2000)
                isAutoLoginCheck = mSharedPrefUtils.getAutoLoginCheck();
>>>>>>> Stashed changes
                isUserIdCheck = !TextUtils.isEmpty(mSharedPrefUtils.getUserId())

                isGoToMain.value = isAutoLoginCheck && isUserIdCheck

            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

}