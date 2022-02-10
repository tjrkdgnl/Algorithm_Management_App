package com.ama.algorithmmanagement.viewmodel

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.fake.FakeSharedPreference
import com.ama.algorithmmanagement.utils.combineWith
import com.ama.algorithmmanagement.utils.getOrAwaitValue
import com.github.mikephil.charting.utils.Utils.init
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
/**
 * author : manyong Han
 * summary : 로그인 화면 뷰모델
 */
class KRLoginViewModel(
    private val mRepository: BaseRepository,
    mLifecycleOwner: LifecycleOwner
) : ViewModel() {

    private val mSharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()

    val isLoginSuccess = MutableLiveData<Boolean>()     // 로그인 성공 여부
    val isAutoCheck = MutableLiveData<Boolean>()        // 자동로그인 체크 여부
    val isMoveToSignUp = MutableLiveData<Boolean>()     // 회원가입 이동 여부

    val isInputDataEmpty = MutableLiveData<Boolean>()   // id,pw 입력 여부
    val isData = MutableLiveData<Boolean>()

    private val checkLoginInfo = combineWith(userId, userPw) {
            id, pwd -> id != null && pwd != null
    }

    private val loginInfoObserver = Observer<Boolean> { }

    init {
        checkLoginInfo.observe(mLifecycleOwner, loginInfoObserver)
    }

    // 로그인 버튼 눌렀을때 유저 정보 체크
    fun getLoginBtnCheck() {
        viewModelScope.launch {
            try {
                checkLoginInfo.value?.let {
                    if (it) {
                        val userInfo = mRepository.getUserInfo(userId.value!!)
                        isLoginSuccess.value =
                            userInfo?.userId.equals(userId.value) && userInfo?.userPw.equals(userPw.value)

                        if (isLoginSuccess.value == null) {
                            isLoginSuccess.value = false
                        }

                        Timber.e("%s, %s", userId.value, userPw.value)
                        if (isAutoCheck.value == null) {
                            isAutoCheck.value = false
                        }

                        if (isLoginSuccess.value!!) {
                            mSharedPref.setAutoLoginCheck(isAutoCheck.value == true)
                            mSharedPref.setUserId(userId.value!!)
                        } else {
                            isLoginSuccess.value = false
                        }
                        Timber.e("로그인 결과 : %s, %s", isLoginSuccess.value!!, isAutoCheck.value)
                        isInputDataEmpty.value = true
                    } else {
                        Timber.e("id 또는 pwd를 다시 확인해주세요.")
                        isInputDataEmpty.value = false
                    }
                }
            } catch (e: Exception) {
                Timber.e("Exception : %s", e.message.toString())
            }
        }
    }

    // 로그인 성공시 유저 정보 저장 및 메인으로 이동
    fun moveToSignUpPage() {
        isMoveToSignUp.value = true
    }

    fun returnCombine(): LiveData<Boolean> {
        return combineWith(userId, userPw) {
                id, pw -> id != null && pw != null
        }
    }

    override fun onCleared() {
        super.onCleared()
        checkLoginInfo.removeObserver(loginInfoObserver)
    }
}