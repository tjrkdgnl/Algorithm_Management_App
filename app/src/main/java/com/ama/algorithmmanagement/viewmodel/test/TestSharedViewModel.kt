package com.ama.algorithmmanagement.viewmodel.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.Network.KAPIGenerator
import kotlinx.coroutines.launch
import timber.log.Timber

class TestSharedViewModel(private val mRepository: BaseRepository) : ViewModel() {
    val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils
    val _userId = MutableLiveData<String>()
    val _userTier = MutableLiveData<String>()
    var _solvedProblems: Problems? = null
    val _tokenCheck = MutableLiveData<Boolean>(false)
    private var token: String? = null
    val _loginCheck = MutableLiveData<Boolean>(false)

    init {
        callSolvedProblems()
    }

    fun callSolvedProblems() {
        viewModelScope.launch {
            _solvedProblems = KAPIGenerator.getInstance().getSolvedProblems("solved_by:skjh0818")
        }
    }

    fun confirmUserId() {
        viewModelScope.launch {
            try {
                _userId.value?.let { id ->
                    val user = mRepository.confirmUserInfo(id)
                    Timber.e(user.toString())
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

        }
    }


    fun saveUserId() {
        _userId.value?.let {
            sharedPref.setUserId(it)
        }
    }

    fun getUserId() {
        Timber.e(sharedPref.getUserId())
    }

    fun deleteUserId() {
        sharedPref.deleteUserId()
    }

    fun saveAutoLoginCheck() {
        _loginCheck.value?.let {
            sharedPref.setAutoLoginCheck(it)
        }
    }

    fun changeTokenState() {
        _tokenCheck.value = true
    }

    fun initTokenState() {
        _tokenCheck.value = false
    }

    fun setToken(solvedacToken: String) {
        token = solvedacToken
    }

    fun getAutoLoginCheck() {
        Timber.e(sharedPref.getAutoLoginCheck().toString())
    }

    fun deleteAutoLoginCheck() {
        sharedPref.deleteAutoLoginCheck()
    }

    fun saveTierType() {
        _userTier.value?.let {
            sharedPref.setTierType(it.toInt())
        }
    }

    fun getTierType() {
        Timber.e(sharedPref.getTierType().toString())
    }

    fun deleteToTierType() {
        sharedPref.deleteToTierType()
    }

    fun saveSolvedacToken() {
        token?.let {
            sharedPref.setSolvedacToken(it)
        }
    }

    fun getSolvedacToken() {
        Timber.e(sharedPref.getSolvedacToken())
    }

    fun deleteSolvedacToken() {
        sharedPref.deleteSolvedacToken()
    }

    fun unSolvedProblems() {
        viewModelScope.launch {
            Timber.e(KAPIGenerator.getInstance().getBOJUserInfo().toString())
        }
    }

    fun saveSolvedProblems() {
        _solvedProblems?.let {
            sharedPref.setSolvedProblems(it)
        }
    }

    fun getSolvedProblems() {
        Timber.e(sharedPref.getSolvedProblems().toString())

    }

    fun deleteSolvedProblems() {
        token?.let {
            KAPIGenerator.initRetrofit(it)
            sharedPref.deleteSolvedProblems()
        }
    }
}