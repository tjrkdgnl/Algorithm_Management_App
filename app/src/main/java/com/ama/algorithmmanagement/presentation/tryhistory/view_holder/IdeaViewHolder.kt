package com.ama.algorithmmanagement.presentation.tryhistory.view_holder

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.IdeaListItemBinding
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import timber.log.Timber
import java.io.File

class IdeaViewHolder(var parent: ViewGroup) :
    KBaseViewHolder<IdeaListItemBinding>(parent, R.layout.idea_list_item) {

    private var mStorage : FirebaseStorage = FirebaseStorage.getInstance()

    fun setData(idea: String?, date:String?, url:String?) {
        binding.idea = idea
        binding.date = date
        if (!TextUtils.isEmpty(url)) {
            binding.ivIdea.visibility = View.VISIBLE
            val imagesRef = mStorage.reference.child("images" + File.separator + url)
            imagesRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    Glide.with(parent.context)
                        .load(it.result)
                        .into(binding.ivIdea)
                } else {
                    Timber.e("이미지 로드 에러")
                }
            }
        } else {
            binding.ivIdea.visibility = View.GONE
        }
    }
}