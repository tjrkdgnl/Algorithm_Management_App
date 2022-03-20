package com.ama.algorithmmanagement.presentation.splash.view_model

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
/**
 * author : manyong Han
 * summary : 스플래시 뷰모델
 */
class KSplashViewModel(
    private val mRepository: BaseRepository
    ) : ViewModel() {
    private val mSharedPrefUtils = AMAApplication.INSTANCE.sharedPrefUtils

    var isUserIdCheck = false
    var isAutoLoginCheck = false

    val isGoToMain = MutableLiveData<Boolean>()

    init {
        getUserLoginInfoCheck()
    }

    // 쉐어드에 유저 아이디 및 자동로그인 여부를 체크
    private fun getUserLoginInfoCheck() {
        viewModelScope.launch {
            try {
                delay(2000) // 스플래시 화면이 안보이고 그냥 넘어가버리는 상황을 방지
                isAutoLoginCheck = mSharedPrefUtils.getAutoLoginCheck()
                isUserIdCheck = !TextUtils.isEmpty(mSharedPrefUtils.getUserId())

                isGoToMain.value = isAutoLoginCheck && isUserIdCheck

            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

}