package com.ama.algorithmmanagement.presentation.tryhistory

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.FragmentIdeaBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseFragment
import com.ama.algorithmmanagement.presentation.tryhistory.adapter.IdeaAdapter
import com.google.firebase.storage.FirebaseStorage
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PagerIdeaFragment(val problemId: Int?) : KBaseFragment<FragmentIdeaBinding>(R.layout.fragment_idea), View.OnClickListener {

    private lateinit var mGetResultText: ActivityResultLauncher<Intent>
    private lateinit var mIdeaInfoViewModel: MyIdeaInfoViewModel
    private lateinit var currentPhotoPath : String
    private lateinit var mPhotoUri : Uri
    private var isVisibleFAB = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGetResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // set progress dialog
                val progressDialog = ProgressDialog(context)
                progressDialog.setTitle("????????? ???..")
                progressDialog.show()

                val currentDate = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
                val fileName = "${mIdeaInfoViewModel.sharedPref.getUserId()}_$currentDate.png"
                val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://algorithmmanagementapp.appspot.com").child("images" + File.separator + fileName)
                storageRef.putFile(mPhotoUri).addOnSuccessListener {
                    if (it.task.isSuccessful) {
                        progressDialog.dismiss()
                        mIdeaInfoViewModel.saveIdeaInfo(fileName, null)
                    }

                }.addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(context, "???????????? ?????????????????????..!", Toast.LENGTH_SHORT)
                        .show()
                }.addOnProgressListener {
                    val progressValue: Double = (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                    // display current progress value
                    progressDialog.setMessage("????????? ??? " + (progressValue.toInt()) + "% ...")
                }

            }
        }

    }

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
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
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

    private fun settingPermission() {
        val permissionListener = object : PermissionListener {
            // ????????? ????????? ???????????? ?????? ???????????? ????????? ???????????? ?????? ????????? ?????? ??????
            override fun onPermissionGranted() {
                Timber.d("?????? ??????")
                // ?????? ???????????? ??????
                setPhotoIdea()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Timber.d("?????? ??????")
                Toast.makeText(context, "????????? ?????? ????????? ????????? ?????????????????????\n????????? ?????????????????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(context)
            .setPermissionListener(permissionListener)
            .setRationaleMessage("?????? ????????? ?????? ????????? ??? ????????? ????????? ?????? ?????????")
            .setDeniedMessage("????????? ?????? ????????? ?????? ????????? ?????????????????????")
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            )
            .check()
    }

    @Throws(IOException::class)
    private fun createImageFile() : File {
        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir : File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply{
            currentPhotoPath = absolutePath
        }
    }

    private fun setPhotoIdea() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (e: IOException) {
                    null
                }
                photoFile?.also {
                    mPhotoUri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.ama.algorithmmanagement.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
                    mGetResultText.launch(takePictureIntent)
                }
            }

        }
    }

    private fun showWriteIdeaDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("??????????????? ???????????????")
        dialogBuilder.setIcon(R.mipmap.ic_launcher)
//        var bindingDialog : DialogIdeaTextBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_idea_text, null, false)
        val view = layoutInflater.inflate(R.layout.dialog_idea_text, null)
        dialogBuilder.setView(view)
        var editTextIdea: EditText = view.findViewById(R.id.et_idea)
        dialogBuilder.setPositiveButton("????????????") { _, _ ->
            if (TextUtils.isEmpty(editTextIdea.text)) {
                Toast.makeText(context, "??????????????? ??????????????????", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            // save idea info
            mIdeaInfoViewModel.saveIdeaInfo(null, editTextIdea.text.toString())
        }
        dialogBuilder.show()
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
            R.id.fab_sub_camera -> { settingPermission() }
        }
    }

}