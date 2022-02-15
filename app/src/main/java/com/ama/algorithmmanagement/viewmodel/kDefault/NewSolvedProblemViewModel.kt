package com.ama.algorithmmanagement.viewmodel.kDefault;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.model.TippingProblemObject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-13
 * class : NewSolvedProblemViewModel.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class NewSolvedProblemViewModel(private val mRepository: BaseRepository) : ViewModel() {

    // 현재 작성하고있는 팁 페이지 번호
    private val _writeTipCurrentPage = MutableLiveData<Int>(0)
    val writeTipCurrentPage: LiveData<Int>
        get() = _writeTipCurrentPage

    // 팁이 마지막 페이지인지여부 (팁을 모두 작성하였는지 여부) 바인딩 어댑터에서 삼항연산자를 통해 건너뛰기 버튼을 보여줄지 안보여줄지 정함
    val _isLastTipPage = MutableLiveData<Boolean>(false)

    // 현재 보여지고 있는 팁작성 페이지 정보
    private val _writeTipProblem = MutableLiveData<TipProblemInfo>()
    val writeTipProblem: LiveData<TipProblemInfo>
        get() = _writeTipProblem

    // 작성하지 않은 팁페이지 (noTipProblem)
    private val _noTipProblem = MutableLiveData<TippingProblemObject>()
    val noTipProblem: LiveData<TippingProblemObject>
        get() = _noTipProblem


    // 문제 보기 눌렀는지 여부 확인할 라이브데이터 ( 문제 보기 누를시 웹뷰 띄워줌)
    private val _isOpenProblemLink = MutableLiveData<Boolean>(false)
    val isOpenProblemLink: LiveData<Boolean>
        get() = _isOpenProblemLink

    // 팁작성하기 editText 양방향 바인딩
    val inputTip = MutableLiveData<String>("")

    // 라디오버튼 양방향 바인딩
    val isShow = MutableLiveData<Boolean>(false)
    val isNotShow = MutableLiveData<Boolean>(false)


    // 메인화면 이동 라이브 데이터
    val moveToMain = MutableLiveData<Boolean>(false)

    // 파베 팁 작성할때 데이터가 저장될때까지 기다리기위해 3가지 상태로 관리 (요청,성공,실패)
    private val _tipSubmitLoading = MutableLiveData<Boolean>(false)
    val tipSubmitLoading: LiveData<Boolean>
        get() = _tipSubmitLoading
    private val _tipSubmitDone = MutableLiveData<Boolean>(false)
    val tipSubmitDone: LiveData<Boolean>
        get() = _tipSubmitDone
    private val _tipSubmitFail = MutableLiveData<Boolean>(false)
    val tipSubmitFail: LiveData<Boolean>
        get() = _tipSubmitFail

    // 작성완료를 눌렀을때 submit 폼이 유효한지여부(팁이 작성되있고,정답을 보고풀었는지 여부 체크하 둘다 만족할때 true)
    private val _onSubmitValid = MutableLiveData<Boolean>()
    val onSubmitValid: LiveData<Boolean>
        get() = _onSubmitValid

    private fun loadNoTippingProblem() {
        viewModelScope.launch {
            _noTipProblem.value = mRepository.getNotTippingProblem()
            // 처음 불러올때 notipproblem list 에서 첫번째 값을 writeTipProblem 으로 지정
            noTipProblem.value?.problemInfoList?.let {
                _writeTipProblem.value = it[0]
            }
            Timber.e("current page ${writeTipCurrentPage.value!!}" +
                    " notipage : ${noTipProblem.value!!.problemInfoList.size}" +
                    " ")
        }
    }

    // 입력폼 초기화
    private fun initInputForm() {
        // 라디오버튼 둘다 비활성화
        isShow.value = false
        isNotShow.value = false
        // editText 비우기
        inputTip.value = ""
        // 요청 성공 실패에대한 결과 초기화
        _tipSubmitFail.value = false
        _tipSubmitDone.value = false
        _tipSubmitLoading.value = false
    }

    // 다음 페이지로 이동
    fun skipTipPage() {
        Timber.e("current page ${writeTipCurrentPage.value!!+1}" +
                " notipage : ${noTipProblem.value!!.problemInfoList.size}" +
                " ")
        // noTipProblem list 를 끝까지 넘겼을때 isLastPage = true (건너뛰기 버튼 사라짐)
        // TODO : 건너뛰기버튼 xml 에서 삼항연산자로 처리했는데 안사라지는 이슈 확인
        if(writeTipCurrentPage.value!!+1==noTipProblem.value!!.problemInfoList.size){
            Timber.e("endpage")
            _isLastTipPage.value = true
        }else{
            noTipProblem.value?.problemInfoList?.let { tipList ->
                if(tipList.size-1 > writeTipCurrentPage.value!!){
                    _writeTipCurrentPage.value = _writeTipCurrentPage.value?.plus(1)
                    _writeTipProblem.value = tipList[writeTipCurrentPage.value!!]
                    initInputForm()
                }
            }
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

    // 팁작성 (파이어베이스에 저장)
    fun submitTip() {
        _tipSubmitLoading.value?.let { isLoading ->
            // 로딩중이 아닐떄 즉, 파베에  저장하고있을때는 submit 하면 안됨
            if (!isLoading) {
                viewModelScope.launch {
                    try {
                        inputTip.value?.let {
                            // 팁, 정답본여부 모두 입력했을때 디비에 저장 시작
                            if (it.isNotEmpty() && (isShow.value!! || isNotShow.value!!)) {
                                Timber.e("작성 폼 $it 답지 본 여부 ${isShow.value}")
                                _tipSubmitLoading.value = true // 로딩 시작(요청 시작)
                                val result = mRepository.modifyTippingProblem(
                                    problemId = writeTipProblem.value?.problem?.problemId!!,
                                    tipComment = it,
                                    isShow = isShow.value!!
                                )
                                _tipSubmitLoading.value = false // 로딩 완료 (요청 끝)
                                _tipSubmitDone.value = true // 요청 성공
                                // 팁을 모두 작성할경우 메인액티비티로 이동
                                if(_isLastTipPage.value!!){
                                    moveToMain.value = true
                                }else{
                                    skipTipPage()
                                }
                            } else {
                                _onSubmitValid.value = false
                            }
                        }
                    } catch (e: Exception) {
                        _tipSubmitFail.value = true// 요청 실패
                        Timber.e(e)
                    }
                }
            }
        }
    }

    init {
        // 팁을 작성하지 않은문제 로딩
        loadNoTippingProblem()
    }


}