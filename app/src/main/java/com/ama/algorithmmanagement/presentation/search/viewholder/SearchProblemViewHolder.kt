package com.ama.algorithmmanagement.presentation.search.viewholder;

import android.content.Intent
import android.view.ViewGroup
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemSearchProblemBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.domain.entity.Keyword
import com.ama.algorithmmanagement.presentation.vpdetail.activity.KViewProblemDetailActivity

/**
 * @author : seungHo
 * @since : 2022-02-14
 * class : SearchProblemViewHolder.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description : 검색 결과 리사이클러뷰 뷰홀더
 */
class SearchProblemViewHolder(private val parent:ViewGroup) :KBaseViewHolder<ItemSearchProblemBinding>(parent,
    R.layout.item_search_problem){
    // TODO : 파이어베이스에서 해당 문제를 풀었다면 체크표시
    fun setData(data:Keyword){
        binding.root.setOnClickListener {
            parent.context.apply{
//                Timber.e("problem ID : ${data.id}")
                val intent = Intent(this,KViewProblemDetailActivity::class.java)

                intent.putExtra("problemId",data.id.toString())
                startActivity(intent)
            }
        }
        binding.keyword = data
        binding.executePendingBindings()

    }
}