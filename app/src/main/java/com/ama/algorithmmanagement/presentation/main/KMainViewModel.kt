package com.ama.algorithmmanagement.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.launch
import timber.log.Timber

class KMainViewModel(private val mRepository: BaseRepository) : ViewModel() {

    private val sharedPref = AMAApplication.INSTANCE.sharedPrefUtils

    // 파이어베이스에 저장된 데이터
    private val _firebaseAllProblems = MutableLiveData<MutableList<TipProblemInfo>>()
    val firebaseAllProblems: LiveData<MutableList<TipProblemInfo>>
        get() = _firebaseAllProblems

    // 백준 API 해결한 문제
    private val _solvedAPIAllProblems = MutableLiveData<MutableList<TaggedProblem>>()
    val solvedAPIALLProblems: LiveData<MutableList<TaggedProblem>>
        get() = _solvedAPIAllProblems

    private val _todaySolvedProblem = MutableLiveData<MutableList<TaggedProblem>>(mutableListOf())
    val todaySolvedProblem: LiveData<MutableList<TaggedProblem>>
        get() = _todaySolvedProblem


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
    val userDateInfo: LiveData<DateObject>
        get() = _userDateInfo

    // 월별 통계 현재 보여지는 날짜 라이브데이터
    private val _dateStatsCurrentDate = MutableLiveData<String>(DateUtils.getCalendarYearMonth())
    val dateStatsCurrentDate: LiveData<String>
        get() = _dateStatsCurrentDate

    // navigation drawer 에대한 상태값
    private val _isOpenDrawer = MutableLiveData<Boolean>(false)
    val isOpenDrawer: LiveData<Boolean>
        get() = _isOpenDrawer

    // 검색 editText 클릭했는지 여부 선택값 (검색 editText 클릭시 검색 activity 등장)
    private val _isClickSearchInput = MutableLiveData<Boolean>(false)
    val isClickSearchInput: LiveData<Boolean>
        get() = _isClickSearchInput

    // solvedAC User info 데이터
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    // 통계에서 유형별 통계 상세보기버튼 눌렀는지 여부
    private val _isCategoryOpen = MutableLiveData<Boolean>(false)
    val isCategoryOpen: LiveData<Boolean>
        get() = _isCategoryOpen


    // 월별 통계데이터 불러오기
    private fun loadUserDateInfo() {
        viewModelScope.launch {
            try {
                _userDateInfo.value = mRepository.getDateObject()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    // navigation drawer 의 상태값 변경
    fun toggleDrawer() {
        this.isOpenDrawer.value?.let {
            _isOpenDrawer.value = !it
        }
    }
    // navigation drawer 열림
    fun openDrawer() {
        _isOpenDrawer.value = true
    }
    // navigation drawer 닫힘
    fun closeDrawer() {
        _isOpenDrawer.value = false
    }

    // 파베에 저장된 문제와 백준 API 에서 해결된 문제랑 비교하여 새로 푼 문제가 있을시 파이어베이스에 저장
    // 즉 solvedAPI 에서 푼 문제랑 파이어베이스에서 저장된 문제랑 동기화 시킴
    private fun syncSolvedApiProblemToFireBase() {
        viewModelScope.launch {
            try {
                // 파이어베이스 문제 세팅
                val problemList = mutableListOf<TipProblemInfo>()
                // 팁작성문제,팁을 작성하지 않은문제 두개를 합쳐서 파베에 저장된 문제 저장
                val noTipProblem = mRepository.getNotTippingProblem()
                val tipProblem = mRepository.getTippingProblem()
                problemList.addAll(noTipProblem?.problemInfoList!!)
                problemList.addAll(tipProblem?.problemInfoList!!)
                _firebaseAllProblems.value = problemList
                // solved ac 해결한 문제 가져옴
                _solvedAPIAllProblems.value = mRepository.getSolvedProblems().problemList!!
                // 새로푼 문제가 있을시
                if (firebaseAllProblems.value?.size != solvedAPIALLProblems.value?.size) {
                    val noTipProblemId = mutableListOf<TaggedProblem>()
                    solvedAPIALLProblems.value?.map { solvedApiProblem ->
                        val findProblem = problemList.find { problem ->
                            problem.problem?.problemId == solvedApiProblem.problemId
                        }
                        if (findProblem == null) {
                            noTipProblemId.add(solvedApiProblem)
                            // 파베에 없는 문제일경우 팁 추가(tipComment 가 없기때문에 자동으로 noTip 에 저장
                            mRepository.setTippingProblem(solvedApiProblem, false, null)
                        }
                    }
                    _todaySolvedProblem.value = noTipProblemId
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    // 오늘 푼 문제 스낵바로 팁작성여부 물어봄
    private fun notifySnackBarTodaySolvedProblem() {
        viewModelScope.launch {
            try {
                // 오늘 푼 문제가 없는데 반복문으로 검사하는건 불필요한 연산이기떄문에 오늘 푼 문제가 있을때만 작동
                // 파베에 저장된 문제 리스트의 사이즈와  백준 API 에 저장된 문제의 리스트 사이즈 비교
                if (firebaseAllProblems.value?.size != solvedAPIALLProblems.value?.size) {
                    val noTipProblems = mRepository.getNotTippingProblem()
                    val saveProblems = mutableListOf<TaggedProblem>()
                    noTipProblems?.problemInfoList?.map { problem ->
                        Timber.e("$problem")
                        Timber.e("${problem.date} == ${DateUtils.getDate()}")
                        if (problem.date == DateUtils.getDate()) {
                            Timber.e("today add")
                            saveProblems.add(problem.problem!!)
                            Timber.e("${todaySolvedProblem.value}")
                        }
                    }
                    _todaySolvedProblem.value = saveProblems
                }
            } catch (e: Exception) {
                Timber.e(e)
            }

        }


    }

    // 유저가 해결한 문제 불러오기
    private fun loadUserSolvedProblemList() {
        viewModelScope.launch {
            try {
                _userSolvedProblems.value = mRepository.getSolvedProblems()
//                Timber.e("load user solved problem ${mRepository.getSolvedProblems()}")
//                Timber.e("solved api ${userSolvedProblem.value}")
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    // 유저가 푼 문제 통계(티어별로 몇개 풀었는지)
    private fun loadUserStatsList() {
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
                val tipProblems = mRepository.getTippingProblem()
                // 0 부터 tipProblem 의 사이즈만큼 rangeList 만든후 shuffle  ex) [0,1,2,3,4,5,6,7] => [5,1,2,0,6,7,3,4] 셔플후
                //  앞에서 4개만 보여줌
                val rangeList = (0 until tipProblems?.count!!).toList().toIntArray();
                rangeList.shuffle()
//                Timber.e("list : ${list.toString()}")
                _retryProblems.value = mutableListOf<TipProblemInfo>(
                    tipProblems.problemInfoList[rangeList[0]],
                    tipProblems.problemInfoList[rangeList[1]],
                    tipProblems.problemInfoList[rangeList[2]],
                    tipProblems.problemInfoList[rangeList[3]],
                )
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    // 백준 API 유저정보 불러옴
    private fun loadSolvedApiUser() {
        viewModelScope.launch {
            try {
                _userInfo.value = mRepository.getBOJUserInfo()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    // 월별통계에서 현재 표시되고 있는 날짜 다음달로 변경
    fun nextMonth() {
        DateUtils.nextMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }

    // 월별통계에서 현재 표시되고 있는 날짜 이전달로 변경
    fun previousMonth() {
        DateUtils.prevMonth()
        _dateStatsCurrentDate.value = DateUtils.getCalendarYearMonth()
    }

    // isClickSearchInput 를 true 바꿈 즉 액티비티에서 searchInput observing 하여 true 가 될경우 검색화면으로 바뀜
    fun moveToSearchAct() {
        _isClickSearchInput.value = true
    }

    // moveToSearchAct() 함수를 통해 화면이 전환될시 상태값은 원래 상태로 돌려놓기위한 함수
    fun initIsClickSearchInput() {
        _isClickSearchInput.value = false
    }

    fun moveToCategoryInfoAct() {
        _isCategoryOpen.value = true
    }

    fun initCategoryInfoAct() {
        _isCategoryOpen.value = false
    }


    // swipe refresh에서 업데이트 시킬데이터
    fun fetchData() {
        loadRetryProblems()
        syncSolvedApiProblemToFireBase()
        notifySnackBarTodaySolvedProblem()
        loadUserStatsList()
        loadUserSolvedProblemList()
        loadUserDateInfo()
    }

    fun isLogin(): Boolean {
        return sharedPref.getUserId() != null
    }

    init {
        loadSolvedApiUser()
        //getUserInfo()
        fetchData()
    }
}