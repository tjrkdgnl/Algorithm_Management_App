package com.ama.algorithmmanagement.Activity.kDefault;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ama.algorithmmanagement.Adapter.TipProblemViewPagerAdapter
import com.ama.algorithmmanagement.Adapter.TipProblemViewPagerFragmentAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.Fragment.SolvedProblemViewPagerFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityNewSolvedProblemViewPagerBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.NewSolvedProblemViewModel
import kotlinx.coroutines.*
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
    KBaseActivity<ActivityNewSolvedProblemViewPagerBinding>(R.layout.activity_new_solved_problem_view_pager) {
    private lateinit var viewModel: NewSolvedProblemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, BaseViewModelFactory(
                RepositoryLocator().getRepository(AMAApplication.INSTANCE)
            )
        )[NewSolvedProblemViewModel::class.java]
        Timber.e("옵 뷰페이져 액티비티")

        binding.viewmodel = viewModel
        val adapter = TipProblemViewPagerFragmentAdapter(this)
//        val adapter = TipProblemViewPagerAdapter(viewModel) // 뷰모델 대신 함수전달
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false // 뷰페어져 스와이프 막기



        // 작성하지 않은 팁을 모두 작성할경우 메인액티비티로 이동
        viewModel.moveToMain.observe(this@NewSolvedProblemActivity, {
            if (it) {
                val intent = Intent(this@NewSolvedProblemActivity, KMainActivity::class.java)
                startActivity(intent)
            }
        })
        // Form 에 모든 데이터를 입력했을시
        viewModel.onValidForm.observe(this,{ isValid->
            if(!isValid){
                Toast.makeText(this,"모두 입력해주세요",Toast.LENGTH_SHORT).show()
            }
        })
        // 페이지 번호가 바뀔시 보여줄 데이터 변경
        viewModel.currentPageNumber.observe(this,{
            // viewModel.currentPageData 가 noTipProblem[it] 번째 데이터로 바뀜
            viewModel.changeCurrentPage(it)
        })
        // 문제 보기를 클릭했을때
        viewModel.isOpenProblemLink.observe(this,{
            if(it){
                viewModel.currentPageData.value?.let{problem->
                    // 인텐트로 WebViewActivity 키고 problemId 넘겨줌
                    val intent = Intent(this,WebViewActivity::class.java)
                    intent.putExtra("problemId",problem.problem?.problemId)
                    startActivity(intent)
                    viewModel.initIsOpenProblemLink() // viewModel openProblemLink 에대한 상태값은 초기화
                }
            }
        })
    }

}