package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.model.TippingProblemObject
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : NewSolvedProblemViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : NewSolvedProblemViewModel 팁 작성화면 뷰모델
 */
class NewSolvedProblemViewModel(private val mRepository: BaseRepository) : ViewModel() {


    // 작성하지 않은 팁페이지 (noTipProblem)
    private val _noTipProblem = MutableLiveData<TippingProblemObject>(TippingProblemObject())
    val noTipProblem: LiveData<TippingProblemObject>
        get() = _noTipProblem

    // 현재 작성하고 있는 페이지의 데이터
    private val _currentPageData = MutableLiveData<TipProblemInfo>()
    val currentPageData: LiveData<TipProblemInfo>
        get() = _currentPageData

    // 현재 작성하고 있는 페이지 번호
    private val _currentPageNumber = MutableLiveData<Int>()
    val currentPageNumber: LiveData<Int>
        get() = _currentPageNumber

    // 다음 페이지가 더 있는지 여부
    private val _hasMoreData = MutableLiveData<Boolean>(true)
    val hasMoreData: LiveData<Boolean>
        get() = _hasMoreData

    // 작성완료를 눌렀을때 submit 폼이 유효한지여부(팁이 작성되있고,정답을 보고풀었는지 여부 체크하 둘다 만족할때 true)
    private val _onValidForm = MutableLiveData<Boolean>(true)
    val onValidForm: LiveData<Boolean>
        get() = _onValidForm

    // 문제 보기 눌렀는지 여부 확인할 라이브데이터 ( 문제 보기 누를시 웹뷰 띄워줌)
    private val _isOpenProblemLink = MutableLiveData<Boolean>()
    val isOpenProblemLink: LiveData<Boolean>
        get() = _isOpenProblemLink

    // 팁작성하기 editText 양방향 바인딩
    val inputTip = MutableLiveData<String>("")

    // 라디오버튼 양방향 바인딩
    val isShow = MutableLiveData<Boolean>(true)
    val isNotShow = MutableLiveData<Boolean>(false)

    // 메인화면 이동 라이브 데이터
    val moveToMain = MutableLiveData<Boolean>(false)

    // 오늘 푼 문제 가져오기
    private fun loadNoTippingProblem() {
        Timber.e("옵 로딩 팁")
        viewModelScope.launch {
            try {
//                val solvedProblem = mRepository.getSolvedProblems()
//                solvedProblem.problemList?.toList()?.let {
//                    // 우선 파베에 내가 푼 문제 업데이트 시킴
//                    mRepository.initTipProblems(it)
//                }
                val getFullNoTipProblemInfo = mRepository.getNotTippingProblem()
                // 깊은복사를 제공하는 Object 에 copy 는 기본자료형만 깊은복사되기때문에 빈리스트 만들어 참조되지 않게함
                val tempNoTip = mutableListOf<TipProblemInfo>()
                Timber.e("data : ${noTipProblem.value!!.problemInfoList}")
                tempNoTip.addAll(getFullNoTipProblemInfo!!.problemInfoList)
                // 데이터 다 지운후 오늘 푼문제만 추가
                _noTipProblem.value!!.problemInfoList.clear()
                tempNoTip.map {
                    // tip 에 저장된 날짜와 오늘날짜가 일치하면 추가
                    if(it.date==DateUtils.getDate()){
                        noTipProblem.value!!.problemInfoList.add(it) // problemList 를 참조한상태에서 add 만 하여 observing 되지 않게함
                    }
                }
                Timber.e("data : ${noTipProblem.value!!.problemInfoList}")
                _noTipProblem.value!!.count = _noTipProblem.value!!.problemInfoList.size
                if(noTipProblem.value!!.problemInfoList.size==0){
                    moveToMain.value = true
                }else{
                    _currentPageData.value = noTipProblem.value!!.problemInfoList[0]
                    _currentPageNumber.value = 0
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    // 다음페이지 이동
    fun skipPage() {
        try {
            if (currentPageNumber.value!! + 1 < noTipProblem.value?.problemInfoList!!.size) {
                Timber.e("next page")
                _currentPageNumber.value = _currentPageNumber.value!! + 1
                // 다음페이지가 없다면 hasMoreData 는 false
                if (currentPageNumber.value!! + 1 == noTipProblem.value?.problemInfoList!!.size) {
                    _hasMoreData.value = false
                }
            } else {
                Timber.e("no page")
                _hasMoreData.value = false
            }
            initForm()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    // 문제보기(웹뷰) 를 클릭시
    fun openProblemLink() {
        _isOpenProblemLink.value = true
    }

    // 문제보기 라이브데이터값 초기화
    fun initIsOpenProblemLink() {
        _isOpenProblemLink.value = false
    }

    // 현재 보여줄 데이터 currentPage data 변경
    fun changeCurrentPage(page: Int) {
        noTipProblem.value?.let {
            _currentPageData.value = it.problemInfoList[page]
        }
    }

    // 입력폼에 값이 모두 입력됬는지
    fun validSubmitForm(tipComment: String, isShow: Boolean, isNotShow: Boolean): Boolean {
        // tipComment 는 공백이면 안되고, 정답 본여부 (라디오버튼) 둘중 하나는 무조건 선택)
        return tipComment.isNotEmpty() && tipComment.isNotBlank() && (isShow || isNotShow)

    }

    private fun initForm() {
        inputTip.value = ""
        isShow.value = true
        isNotShow.value = false
    }

    private var tipSubmitJob: Job? = null

    // 팁작성 (파이어베이스에 저장)
    fun submitForm() {
        tipSubmitJob?.cancel()
        tipSubmitJob = viewModelScope.launch {
            //kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled;
            // job 이 cancel 됬을때 suspend 함수를 호출하면 발생하는 예외
            tipSubmitJob?.join() // job 이 끝날때까지 대기
            try {
                if (validSubmitForm(
                        tipComment = inputTip.value!!,
                        isShow = isShow.value!!,
                        isNotShow = isNotShow.value!!
                    )
                ) {
                    Timber.e("작성")
                    mRepository.modifyTippingProblem(
                        problemId = currentPageData.value?.problem!!.problemId,
                        isShow = isShow.value!!,
                        tipComment = inputTip.value
                    )
                    _onValidForm.value = true
                    skipPage()
                    if (hasMoreData.value == false) {
                        moveToMain.value = true
                    }
                } else {
                    Timber.e("실패")
                    _onValidForm.value = false
                }
                initForm()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

    init {
        // 팁을 작성하지 않은문제 로딩
        loadNoTippingProblem()
    }
}
