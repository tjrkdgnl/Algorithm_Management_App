package com.ama.algorithmmanagement.Activity.kDefault

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.TryFailedAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityTryFailedBinding
import com.ama.algorithmmanagement.model.TaggedProblem
import com.ama.algorithmmanagement.viewmodel.kDefault.TryFailedViewModel
import timber.log.Timber

/**
 * 시도했으나 실패한 문제
 * author hongdroid94
 */
class TryFailedActivity : KBaseActivity<ActivityTryFailedBinding>(R.layout.activity_try_failed) {

    private lateinit var tryFailedViewModel: TryFailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryFailedViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[TryFailedViewModel::class.java]

        binding.viewModel = tryFailedViewModel
        binding.rvTryFailed.adapter = TryFailedAdapter(listClickListener)
    }

    private val listClickListener : (TaggedProblem) -> Unit = { clickProblem ->
        Timber.d("problemId : ${clickProblem.problemId}")

        val strOptionArry = arrayOf("문제보기 (코멘트 작성화면)", "문제 풀이 히스토리")
        val builder = AlertDialog.Builder(this)
        builder.setItems(strOptionArry) { _, position ->
            when (position) {
                0-> {
                    // 문제보기 (코멘트 작성화면)

                }
                1-> {
                    // 문제 풀이 히스토리
                    val intent = Intent(this, TryHistoryActivity::class.java)
                    intent.putExtra("problemId", clickProblem.problemId)
                    startActivity(intent)
                }
            }
        }
        builder.show()

    }
}