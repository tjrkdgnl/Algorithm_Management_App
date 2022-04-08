package com.ama.algorithmmanagement.presentation.search;

import android.view.ViewGroup
import android.widget.Toast
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.databinding.ItemSearchProblemBinding
import com.ama.algorithmmanagement.domain.base.KBaseViewHolder
import com.ama.algorithmmanagement.domain.entity.Keyword

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
                Toast.makeText(this,"${data.id} 화면으로 이동",Toast.LENGTH_SHORT).show()
//                Timber.e("problem ID : ${data.id}")
//                val intent = Intent(this,액티비티)
//                intent.putExtra("problemId",data.id)
//                startActivity(intent)
            }
        }
        binding.keyword = data
        binding.executePendingBindings()

    }
}