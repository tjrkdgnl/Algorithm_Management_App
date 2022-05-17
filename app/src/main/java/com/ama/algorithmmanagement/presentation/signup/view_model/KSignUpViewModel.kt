package com.ama.algorithmmanagement.presentation.signup.view_model

import android.widget.Toast
import androidx.lifecycle.*
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
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
    private val mSharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    val isGoToRegisterFinal = MutableLiveData<Boolean>()

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val fcmToken = MutableLiveData<String>()
    val solvedacToken = MutableLiveData<String>()
    val isGetFcmToken = MutableLiveData<Boolean>()

    val isMoveToWebView = MutableLiveData<Boolean>()
    val isConnectSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess = MutableLiveData<Boolean>()
    val isGetSuccessSolvToken = MutableLiveData<Boolean>()

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
        isMoveToWebView.value = false
        isGetFcmToken.value = true
    }

    // 회원가입 마지막 화면으로 이동할 때
    fun moveToRegisterFrag() {
        isGoToRegisterFinal.value = true
        isGetFcmToken.value = true
    }

    fun getSolvedacToken(token: String) {
        solvedacToken.value = token
        isGetSuccessSolvToken.value = true
        isConnectSuccess.value = true
    }

    // 백준 등록여부 체크 -> AMA에 등록되어있는지 체크 -> true: 이미 가입, false: 가입되어있지 않음
    // api 리턴 결과로 계정 연동 여부 확인하여 회원가입 성공유무 판단.
    fun registerAMA() {
        viewModelScope.launch {
            checkUserInfo.value?.let { checkIdAndPw ->
                solvedacToken.value?.let { sToken ->
                    if (checkIdAndPw) {
                        val confirm = mRepository.confirmUserInfo(userId.value!!)
                        Timber.e("백준 등록 여부 : $confirm")
                        if (confirm) {
                            val isAlreadySignUp = mRepository.signUpUserInfo(
                                userId.value!!, userPw.value!!
                            )
                            Timber.e("AMA 등록 여부 : $isAlreadySignUp")
                            if (isAlreadySignUp) {
                                val signUp = mRepository.setUserInfo(
                                    userId.value!!, userPw.value!!,
                                    fcmToken.value.toString(), sToken)
                                Timber.e("가입 성공 여부 : $signUp")
                                isRegisterSuccess.value = signUp
                                mSharedPref.setUserId(userId.value!!)
                                mSharedPref.setSolvedacToken(sToken)
                            } else {
                                Toast.makeText(
                                    AMAApplication.INSTANCE.applicationContext,
                                    "이미 가입된 계정입니다.", Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                AMAApplication.INSTANCE.applicationContext,
                                "백준 등록 여부를 확인해주세요.", Toast.LENGTH_SHORT
                            ).show()
                        }
                        Timber.e(mRepository.getUserInfo(userId.value!!).toString())
                    } else {
                        Timber.e("id 또는 pwd를 다시 확인해주세요.")
                        Toast.makeText(
                            AMAApplication.INSTANCE.applicationContext,
                            "아이디, 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } ?: Toast.makeText(
                AMAApplication.INSTANCE.applicationContext,
                "아이디, 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT
            ).show() // 엘비스 연산자.
        }
    }

    override fun onCleared() {
        super.onCleared()
        checkUserInfo.removeObserver(userInfoObserver)
    }
}