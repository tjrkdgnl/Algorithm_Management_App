package com.ama.algorithmmanagement.Activity.kDefault;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.TipProblemViewPagerAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNewSolvedProblemBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-02-12
 * class : NewSolvedProblem.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 팁 작성 액티비티 뷰페이져로 팁을 작성하지 않은문제를 보여주고 팁을 작성할수 있음 건너뛰기를 할경우 다음문제의 팁을 작성할수 있음
 */
class NewSolvedProblemActivity :
    KBaseActivity<ActivityNewSolvedProblemBinding>(R.layout.activity_new_solved_problem) {
    private lateinit var viewModel: NewSolvedProblemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, BaseViewModelFactory(
                RepositoryLocator().getRepository(AMAApplication.INSTANCE)
            )
        )[NewSolvedProblemViewModel::class.java]
        binding.apply {

            viewmodel = viewModel

            val adapter = TipProblemViewPagerAdapter(viewModel)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false // 뷰페어져 스와이프 막기

            // 현재 보여줄 페이지 writeTipCurrentPage 가 바뀔때마다 뷰페이져 포지션도 바꿈
            viewModel.writeTipCurrentPage.observe(this@NewSolvedProblemActivity, {
                viewPager.currentItem = it
            })

            // isOpenProblemLink 가 감지됬을때 (문제보기를 눌렀을때)
            viewModel.isOpenProblemLink.observe(this@NewSolvedProblemActivity, { isClick ->
                if (isClick) {
                    val intent = Intent(this@NewSolvedProblemActivity, WebViewActivity::class.java)
                    // 현재 문제의 아이디값을 intent 로 넘김
                    intent.putExtra(
                        "problemId",
                        viewModel.writeTipProblem.value?.problem?.problemId
                    )
                    startActivity(intent)
                    // isOpenProblemLink 값은 초기화시켜줌
                    viewModel.initIsOpenProblemLink()
                }
            })
            // 팁을 파베에 저장중일때
            viewModel.tipSubmitLoading.observe(this@NewSolvedProblemActivity, {
                if (it) {
                    makeToast("팁을 데이터베이스에 저장하는중입니다.")
                }
            })
            // 팁을 파베에 저장을 했을때
            viewModel.tipSubmitDone.observe(this@NewSolvedProblemActivity, {
                if (it) {
                    makeToast("팁 작성을 성공하였습니다.")
                }
            })
            // 팁을 파베저장에 실패했을때
            viewModel.tipSubmitFail.observe(this@NewSolvedProblemActivity, {
                if (it) {
                    makeToast("팁작성을 실패하였습니다.")
                }
            })
            // 팁 작성할때 모두 입력하지 않았을때
            viewModel.onSubmitValid.observe(this@NewSolvedProblemActivity, {
                if (!it) {
                    makeToast("정답을 보고풀었는지 여부와 팁을 모두 작성해주세요")
                }
            })
            // 작성하지 않은 팁을 모두 작성할경우 메인액티비티로 이동
            viewModel.moveToMain.observe(this@NewSolvedProblemActivity, {
                if (it) {
                    val intent = Intent(this@NewSolvedProblemActivity, KMainActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    // 토스트메시지 띄워줄 함수
    private fun makeToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

}