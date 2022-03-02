package com.ama.algorithmmanagement.viewmodel.kDefault

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.base.BaseRepository
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 회원가입 화면 뷰모델 (액티비티, 프래그먼트 공용)
 */

class KSignUpViewModel(
    private val mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner
    ) : ViewModel() {
    val isGoToRegisterFinal = MutableLiveData<Boolean>()

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()
    val isGetFcmToken = MutableLiveData<Boolean>()

    val isMoveToWebView = MutableLiveData<Boolean>()
    val isConnectSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess = MutableLiveData<Boolean>()
    val isInputDataEmpty = MutableLiveData<Boolean>()

    private val checkUserInfo = combineWith(userId, userPw) { id, pwd -> id != null && pwd != null }
    private val userInfoObserver = Observer<Boolean> { }

    init {
        checkUserInfo.observe(mLifecycleOwner, userInfoObserver)
    }

    // 연동하기 버튼 눌렀을때
    fun moveToWebView() {
        isMoveToWebView.value = true
        isGetFcmToken.value = true
    }

    // 웹뷰 닫았을때 연동에 성공한 것으로 체크
    fun connectBojSuccess() {
        isConnectSuccess.value = true
        isGetFcmToken.value = true
    }

    // 회원가입 마지막 화면으로 이동할 때
    fun moveToRegisterFrag() {
        isGoToRegisterFinal.value = true
        isGetFcmToken.value = true
    }

    // 백준 등록여부 체크 -> AMA에 등록되어있는지 체크 -> true: 이미 가입, false: 가입되어있지 않음
    // api 리턴 결과로 계정 연동 여부 확인하여 회원가입 성공유무 판단.
    fun registerAMA() {
        viewModelScope.launch {
            try {
                checkUserInfo.value?.let {
                    if (it) {
                        val confirm = mRepository.confirmUserInfo(userId.value!!)
                        Timber.e("백준 등록 여부 : $confirm")
                        if (confirm) {
                            val isUserCheck = mRepository.signUpUserInfo(
                                userId.value!!, userPw.value!!)
                            Timber.e("AMA 등록 여부 : $confirm")
                            if(isUserCheck) {
                                val signUp = mRepository.setUserInfo(
                                    userId.value!!,
                                    userPw.value!!,
                                    fcmToken.value!!
                                )
                                Timber.e("가입 성공 여부 : $signUp")
                                isRegisterSuccess.value = signUp
                            }
                        }
                        Timber.e(mRepository.getUserInfo(userId.value!!).toString())
                        isInputDataEmpty.value = true
                    } else {
                        Timber.e("id 또는 pwd를 다시 확인해주세요.")
                        isInputDataEmpty.value = false
                    }
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        checkUserInfo.removeObserver(userInfoObserver)
    }
}