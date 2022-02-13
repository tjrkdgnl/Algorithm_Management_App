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
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import kotlin.math.log

class KMainViewModel(private val mRepository:BaseRepository) : ViewModel() {

    // 다시 풀어볼 문제 라이브데이터
    private val _retryProblems = MutableLiveData<MutableList<TipProblemInfo>>()
    val retryProblems: LiveData<MutableList<TipProblemInfo>>
        get() = _retryProblems

    // 유저 통계 (티어별 통계) 라이브데이터
    private val _userStats = MutableLiveData<List<Stats>>()
    val userStats: LiveData<List<Stats>>
        get() = _userStats

    // 해결한 문제 라이브데이터
    private val _userSolvedProblems = MutableLiveData<Problems>()
    val userSolvedProblem: LiveData<Problems>
        get() = _userSolvedProblems

    // 월별 통계 라이브 데이터
    private val _userDateInfo = MutableLiveData<DateObject>()
    val userDateInfo:LiveData<DateObject>
        get() = _userDateInfo

    // 월별 통계 현재 보여지는 날짜 라이브데이터
    private val _dateStatsCurrentDate = MutableLiveData<String>(DateUtils.getCalendarYearMonth())
    val dateStatsCurrentDate:LiveData<String>
        get() = _dateStatsCurrentDate

    // navigation drawer 에대한 상태값
    private val _isOpenDrawer = MutableLiveData<Boolean>(false)
    val isOpenDrawer:LiveData<Boolean>
        get() = _isOpenDrawer

    // 월별 통계데이터 불러오기
    private  fun loadUserDateInfo(){
        viewModelScope.launch {
            try{
                _userDateInfo.value = mRepository.getDateObject()
            }catch (e:Exception){
                Timber.e(e.message.toString())
            }
        }
    }
    // navigation drawer 의 상태값 변경
    fun toggleDrawer(){
        this.isOpenDrawer.value?.let{
            _isOpenDrawer.value = !it
        }
    }
    fun openDrawer(){
        _isOpenDrawer.value = true
    }
    fun closeDrawer(){
        _isOpenDrawer.value = false
    }
    // 유저가 해결한 문제 불러오기
    private  fun loadUserSolvedProblemList() {
        viewModelScope.launch {
            try {
                _userSolvedProblems.value = mRepository.getSolvedProblems()
                Timber.e("load user solved problem ${mRepository.getSolvedProblems()}")
                Timber.e("solved api ${userSolvedProblem.value}")
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    // 유저가 푼 문제 통계(티어별로 몇개 풀었는지)
    private  fun loadUserStatsList() {
        viewModelScope.launch {
            try {
                _userStats.value = mRepository.getUserStats()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    // 다시 풀어볼 문제
    private fun loadRetryProblems() {
        viewModelScope.launch {
            try {
                val list = mRepository.getNotTippingProblem()
                Timber.e("list : ${list.toString()}")
                _retryProblems.value = list?.problemInfoList
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }
    // 월별통계에서 현재 표시되고 있는 날짜 다음달로 변경
    fun nextMonth(){
        DateUtils.nextMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }
    // 월별통계에서 현재 표시되고 있는 날짜 이전달로 변경
    fun previousMonth(){
        DateUtils.prevMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }

    // 유저 회원가입
    fun setUserId(){
        viewModelScope.launch {
            val result = mRepository.setUserInfo("seungho0510","1234","")
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