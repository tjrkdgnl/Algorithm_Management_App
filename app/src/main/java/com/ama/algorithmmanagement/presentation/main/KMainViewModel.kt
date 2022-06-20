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

    // 다시풀어볼 문제 자세히 보기 눌렀는지 여부
    private val _isRetryProblemsInfo = MutableLiveData<Boolean>(false)
    val isRetryProblemsInfo: LiveData<Boolean>
        get() = _isRetryProblemsInfo

    private lateinit var solvedProblems: List<TaggedProblem>

    init {
        viewModelScope.launch {
            try {
                _userInfo.value = mRepository.getBOJUserInfo()
                solvedProblems = mRepository.getSolvedProblems().problemList ?: emptyList()
                _userStats.value = mRepository.getUserStats()
                _userDateInfo.value = mRepository.getDateObject()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }

        loadRetryProblems()
        syncSolvedApiProblemToFireBase()
        notifySnackBarTodaySolvedProblem()
    }

    fun fetchData() {
        syncSolvedApiProblemToFireBase()
        notifySnackBarTodaySolvedProblem()
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

    fun openRetryProblemsInfo() {
        _isRetryProblemsInfo.value = true
    }

    fun initRetryProblemsInfo() {
        _isRetryProblemsInfo.value = false
    }

    // 파베에 저장된 문제와 백준 API 에서 해결된 문제랑 비교하여 새로 푼 문제가 있을시 파이어베이스에 저장
    // 즉 solvedAPI 에서 푼 문제랑 파이어베이스에서 저장된 문제랑 동기화 시킴
    private fun syncSolvedApiProblemToFireBase() {
        viewModelScope.launch {
            try {
                // 파이어베이스 문제 세팅
                val firebaseProblems =
                    mRepository.getAllTipProblems()?.problemInfoList ?: emptyList()

                if (firebaseProblems.isEmpty()) {
                    mRepository.initTipProblems(solvedProblems)
                    return@launch
                }

                // 새로푼 문제가 있을시
                if (firebaseProblems.size < solvedProblems.size) {
                    loop@ for (baekjoonProblem in solvedProblems) {
                        var isSolved = false
                        for (firebaseProblem in firebaseProblems) {
                            firebaseProblem.problem?.let { fbProblem ->
                                // 파베 팁 문제 아이디랑 백준 API 에서 푼문제랑 비교해서 같지 않으면
                                if (fbProblem.problemId == baekjoonProblem.problemId) {
                                    isSolved = true
                                    return@let
                                }
                            }
                            if (isSolved) {
                                continue@loop
                            }
                        }
                        // 푼문제가 아닐경우 리스트에 추가
                        mRepository.setTippingProblem(baekjoonProblem, false, null)
                    }
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
                val noTipProblems = mRepository.getNotTippingProblem()
                val saveProblems = mutableListOf<TaggedProblem>()

                noTipProblems?.problemInfoList?.forEach {
                    if (it.date == DateUtils.getDate()) {
                        it.problem?.let {
                            saveProblems.add(it)
                        }
                    }
                }

                _todaySolvedProblem.value = saveProblems
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    // 다시 풀어볼 문제
    private fun loadRetryProblems() {
        viewModelScope.launch {
            try {
                val tipProblems = mRepository.getAllTipProblems()?.problemInfoList ?: return@launch

                // 0 부터 tipProblem 의 사이즈만큼 rangeList 만든후 shuffle  ex) [0,1,2,3,4,5,6,7] => [5,1,2,0,6,7,3,4] 셔플후
                //  앞에서 4개만 보여줌
                val data = tipProblems.toList().shuffled().take(4)
                _retryProblems.value = data.toMutableList()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
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

    fun isLogin(): Boolean {
        return sharedPref.getUserId() != null
    }
}