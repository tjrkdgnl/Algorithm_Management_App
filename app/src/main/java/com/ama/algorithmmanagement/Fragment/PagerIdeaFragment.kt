package com.ama.algorithmmanagement.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.marginLeft
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.IdeaAdapter
import com.ama.algorithmmanagement.Adapter.test.TestIdeaAdpater
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseFragment
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentIdeaBinding
import com.ama.algorithmmanagement.viewmodel.kDefault.MyIdeaInfoViewModel

class PagerIdeaFragment(val problemId: Int?) : KBaseFragment<FragmentIdeaBinding>(R.layout.fragment_idea), View.OnClickListener {

    lateinit var mIdeaInfoViewModel: MyIdeaInfoViewModel
    var isVisibleFAB = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mIdeaInfoViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE), this)
        )[MyIdeaInfoViewModel::class.java]

        mIdeaInfoViewModel.setProblemId(problemId)

        binding.fabAddIdea.setOnClickListener(this)
        binding.fabSubText.setOnClickListener(this)
        binding.fabSubCamera.setOnClickListener(this)

        binding.viewModel = mIdeaInfoViewModel
        binding.rvMyIdea.adapter = IdeaAdapter()
        binding.rvMyIdea.setHasFixedSize(false)

        mIdeaInfoViewModel.ideaInfos.observe(viewLifecycleOwner,{ ideas ->
            ideas?.let {
                mIdeaInfoViewModel.ideaList.addAll(it.ideaList)
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.fab_add_idea -> {
                if (!isVisibleFAB) {
                    isVisibleFAB = true
                    binding.fabSubText.visibility = View.VISIBLE
                    binding.fabSubCamera.visibility = View.VISIBLE
                } else {
                    isVisibleFAB = false
                    binding.fabSubText.visibility = View.GONE
                    binding.fabSubCamera.visibility = View.GONE
                }
            }
            R.id.fab_sub_text -> { showWriteIdeaDialog() }
            R.id.fab_sub_camera -> { setPhotoIdea() }
        }
    }

    private fun setPhotoIdea() {
        // todo 카메라 인텐트 처리
    }

    private fun showWriteIdeaDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("아이디어를 남겨주세요")
        dialogBuilder.setIcon(R.mipmap.ic_launcher)
        val view = layoutInflater.inflate(R.layout.dialog_idea_text, null)
        view
        dialogBuilder.setView(view)
        var editTextIdea: EditText = view.findViewById(R.id.et_idea)
        dialogBuilder.setPositiveButton("작성완료") { _, _ ->
            if (TextUtils.isEmpty(editTextIdea.text)) {
                Toast.makeText(context, "입력필드가 비어있습니다", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            // save idea info
            mIdeaInfoViewModel.saveIdeaInfo(null, editTextIdea.text.toString())

        }
        dialogBuilder.show()
    }

}