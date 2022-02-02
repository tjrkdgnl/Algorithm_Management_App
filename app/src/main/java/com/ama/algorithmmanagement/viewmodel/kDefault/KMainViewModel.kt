package com.ama.algorithmmanagement.viewmodel.kDefault

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.fake.FakeFirebaseDataProvider
import com.ama.algorithmmanagement.fake.FakeFirebaseReference
import com.ama.algorithmmanagement.fake.FakeRepository
import com.ama.algorithmmanagement.fake.FakeSharedPreference
import com.ama.algorithmmanagement.model.DateInfoObject
import com.ama.algorithmmanagement.model.Problems
import com.ama.algorithmmanagement.model.Stats
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import kotlin.math.log

class KMainViewModel(private val mRepository:BaseRepository) : ViewModel() {

    private val fakeFirebaseDataProvider = FakeFirebaseDataProvider(AMAApplication.INSTANCE)

    private val _retryProblems = MutableLiveData<MutableList<TipProblemInfo>>()
    val retryProblems: LiveData<MutableList<TipProblemInfo>>
        get() = _retryProblems


    private val _userStats = MutableLiveData<List<Stats>>()
    val userStats: LiveData<List<Stats>>
        get() = _userStats

    private val _userSolvedProblems = MutableLiveData<Problems>()
    val userSolvedProblem: LiveData<Problems>
        get() = _userSolvedProblems

    private val _userDateInfo = MutableLiveData<DateInfoObject>()
    val userDateInfo:LiveData<DateInfoObject>
        get() = _userDateInfo

    private val _dateStatsCurrentDate = MutableLiveData<String>(DateUtils.getCalendarYearMonth())
    val dateStatsCurrentDate:LiveData<String>
        get() = _dateStatsCurrentDate

    private val _isOpenDrawer = MutableLiveData<Boolean>(false)
    val isOpenDrawer:LiveData<Boolean>
        get() = _isOpenDrawer

    private  fun loadUserDateInfo(){
        viewModelScope.launch {
            try{
                _userDateInfo.value = mRepository.getDateInfoObject()
                Timber.e("date info ${mRepository.getDateInfoObject()?.dateList}")
            }catch (e:Exception){
                Timber.e(e.message.toString())
            }
        }
    }
    fun toggleOpenDrawer(){
        this.isOpenDrawer.value?.let{
            _isOpenDrawer.value = !it
        }
    }
    private  fun loadUserSolvedProblemList() {
        viewModelScope.launch {
            try {
                _userSolvedProblems.value = mRepository.getSolvedProblems()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    private  fun loadUserStatsList() {
        viewModelScope.launch {
            try {
                _userStats.value = mRepository.getUserStats()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    private fun loadRetryProblems() {
        viewModelScope.launch {
            try {
                val list = mRepository.getTippingProblem()
                Timber.e("lis ${list.toString()}")
                _retryProblems.value = list?.problemInfoList
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
    fun nextMonth(){
        DateUtils.nextMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }
    fun previousMonth(){
        DateUtils.prevMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }

    fun setUserId(){
        viewModelScope.launch {
            mRepository.setUserInfo("seungho0510","1234","")
        }
    }

    init {
        setUserId()
        loadUserStatsList()
        loadRetryProblems()
        loadUserSolvedProblemList()
        loadUserDateInfo()
    }
}