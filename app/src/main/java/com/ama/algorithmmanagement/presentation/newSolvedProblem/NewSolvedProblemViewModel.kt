package com.ama.algorithmmanagement.presentation.newSolvedProblem;

import androidx.lifecycle.*
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.domain.entity.TippingProblemObject
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.Job
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

    // Mediator Live Data 에서 addSource 한 라이브데이터가 변경될때마다 _inputFormData() 함수를 호출해서 isValidForm(입력폼이 모두 입력됬는지 여부)
    // 값을 업데이트하게됨, activity 에서 onValidForm 을 observing 하면 inputForm 에서 상태가 변할때마다 감지되기떄문에 submit 버튼을 눌렀을때만
    // observing 되는 라이브데이터
    private val _onSubmitValidForm = MutableLiveData<Boolean>(true)
    val onSubmitValidForm:LiveData<Boolean>
        get() = _onSubmitValidForm

    // 문제 보기 눌렀는지 여부 확인할 라이브데이터 ( 문제 보기 누를시 웹뷰 띄워줌)
    private val _isOpenProblemLink = MutableLiveData<Boolean>()
    val isOpenProblemLink: LiveData<Boolean>
        get() = _isOpenProblemLink

    val inputFormLiveData: MediatorLiveData<Any> = MediatorLiveData()

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
                val getFullNoTipProblemInfo = mRepository.getNotTippingProblem()
                // 깊은복사를 제공하는 Object 에 copy 는 기본자료형만 깊은복사되기때문에 빈리스트 만들어 참조되지 않게함
                val tempNoTip = mutableListOf<TipProblemInfo>()
                _noTipProblem.value?.let { noTip ->
                    Timber.e("data : ${noTip.problemInfoList}")
                    // 데이터 다 지운후 오늘 푼문제만 추가
                    getFullNoTipProblemInfo?.problemInfoList?.map {
                        // tip 에 저장된 날짜와 오늘날짜가 일치하면 추가
                        if (it.date == DateUtils.getDate()) {
                            tempNoTip.add(it) // problemList 를 참조한상태에서 add 만 하여 observing 되지 않게함
                        }
                    }
                    noTip.problemInfoList.addAll(tempNoTip)
                    noTip.count = noTip.problemInfoList.size
                    if (noTip.problemInfoList.size == 0) {
                        moveToMain.value = true
                    } else {
                        _currentPageData.value = noTip.problemInfoList[0]
                        _currentPageNumber.value = 0
                        // 오늘 푼 문제가 한개일경우 건너뛰기 버튼이 보이는 상황 예외 처리
                        if (noTip.problemInfoList.size == 1) {
                            _hasMoreData.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    // 다음페이지 이동
    fun skipPage() {
        try {
            currentPageNumber.value?.let { pageNum ->
                if (pageNum + 1 < noTipProblem.value?.problemInfoList!!.size) {
                    Timber.e("next page")
                    _currentPageNumber.value = pageNum + 1
                    // 다음페이지가 없다면 hasMoreData 는 false
                    if (pageNum + 1 == noTipProblem.value?.problemInfoList!!.size) {
                        _hasMoreData.value = false
                    }
                } else {
                    Timber.e("no page")
                    _hasMoreData.value = false
                }
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
                //Mediator 라이브데이터로 입력값(팁,정답본여부)을 addSource 하고
                onValidForm.value?.let{ isValidForm->
                    if (isValidForm) {
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
                        _onSubmitValidForm.value = true
                    } else {
                        Timber.e("실패")
                        _onValidForm.value = false
                        _onSubmitValidForm.value = false
                    }
                    initForm()
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

    private fun checkInputFormLiveData(): Boolean {
        Timber.e("함수호출")
        try {
            inputTip.value?.let { inputTip ->
                val result = inputTip.isNotEmpty() && inputTip.isNotBlank() && (isNotShow.value!! || isShow.value!!)
                Timber.e("tip : $inputTip 정답봄 : ${isShow.value!!} 정답 안봄 : ${isNotShow.value!!}" )
                Timber.e("팁 작성 가능 ? :$result")
                return result
            }
            return false
        } catch (e: Exception) {
            Timber.e(e)
            return false
        }

    }

    init {
        // 팁을 작성하지 않은문제 로딩
        loadNoTippingProblem()
        inputFormLiveData.addSource(inputTip) {
            Timber.e("inputTip 라이브데이터 추가 값 : $it")
            _onValidForm.value = checkInputFormLiveData()
        }
        inputFormLiveData.addSource(isShow) {
            Timber.e("isShow 라이브데이터 추가 값 :$it")
            _onValidForm.value = checkInputFormLiveData()

        }
        inputFormLiveData.addSource(isNotShow) {
            Timber.e("isNotShow 라이브데이터 추가 값 :$it")
            _onValidForm.value = checkInputFormLiveData()
        }

    }
}
