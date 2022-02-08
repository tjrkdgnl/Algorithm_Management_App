package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.*
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.utils.combineWith
import kotlinx.coroutines.launch
import timber.log.Timber

class TestRealViewModel(
    private val mRepository: BaseRepository,
    private val mLifecycleOwner: LifecycleOwner
) : ViewModel() {
    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val fcmToken = MutableLiveData<String>()
    val checkUserInfo = combineWith(userId, password) { id, pwd -> id != null && pwd != null }
    private val userInfoObserver = Observer<Boolean> { }

    val problemId = MutableLiveData<String>()

    val solvedacToken = MutableLiveData<String>()

    init {
        checkUserInfo.observe(mLifecycleOwner, userInfoObserver)
    }

    fun saveUserInfo() {
        viewModelScope.launch {
            checkUserInfo.value?.let {
                if (it) {
                    mRepository.setUserInfo(userId.value!!, password.value!!)

                } else {
                    Timber.e("id 또는 pwd를 다시 확인해주세요.")
                }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val userInfo = mRepository.getUserInfo()

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getProblem() {
        viewModelScope.launch {
            try {
                mRepository.getProblem(problemId.value!!.toInt())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getStats() {
        viewModelScope.launch {
            try {
                mRepository.getUserStats()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getSolvedProblems() {
        viewModelScope.launch {
            try {
                mRepository.getSolvedProblems()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getSearchProblemList() {
        viewModelScope.launch {
            try {
                mRepository.getSearchProblemList(problemId.value!!.toInt())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun getUnSolvedProblems() {
        viewModelScope.launch {
            try {
                mRepository.getUnSolvedProblems(solvedacToken.value).toString()

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun setSolvedacToken(token: String?) {
        solvedacToken.value = token
    }

    override fun onCleared() {
        super.onCleared()

        checkUserInfo.removeObserver(userInfoObserver)
    }
}