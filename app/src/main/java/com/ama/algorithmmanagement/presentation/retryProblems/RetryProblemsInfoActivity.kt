package com.ama.algorithmmanagement.presentation.retryProblems;

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityRetryProblemsBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.presentation.retryProblems.adapter.RetryProblemsInfoAdapter
import com.ama.algorithmmanagement.presentation.tryhistory.TryHistoryActivity
import com.ama.algorithmmanagement.presentation.vpdetail.activity.KViewProblemDetailActivity

/**
 * @author SeungHo Lee
 * summary :
 */
class RetryProblemsInfoActivity :
    KBaseActivity<ActivityRetryProblemsBinding>(R.layout.activity_retry_problems) {
    private lateinit var viewModel: RetryProblemsInfoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[RetryProblemsInfoViewModel::class.java]
        binding.viewmodel = viewModel
        val adapter =  RetryProblemsInfoAdapter()

        binding.rvRetryProblemInfo.adapter = adapter
        adapter.setOnItemClickListener(object:RetryProblemsInfoAdapter.OnRetryProblemClickListener{
            override fun onClick(v: View, data: TipProblemInfo) {
                val builder = AlertDialog.Builder(this@RetryProblemsInfoActivity)
                builder.setItems(arrayOf("문제보기 (코멘트 작성화면)","문제풀이 히스토리")
                ) { _, p1 ->
                    when(p1){
                        0->{
                            val intent = Intent(applicationContext,KViewProblemDetailActivity::class.java)
                            intent.putExtra("problemId",data.problem?.problemId.toString())
                            startActivity(intent)
                        }
                        1->{
                            val intent = Intent(applicationContext,TryHistoryActivity::class.java)
                            intent.putExtra("problemId",data.problem?.problemId)
                            startActivity(intent)
                        }
                    }
                }
                builder.show()
            }
        })
    }
}