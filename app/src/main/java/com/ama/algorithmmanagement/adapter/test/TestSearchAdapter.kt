package com.ama.algorithmmanagement.adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.test.TestSearchViewHolder
import com.ama.algorithmmanagement.data.model.Keyword
import timber.log.Timber

class TestSearchAdapter : RecyclerView.Adapter<TestSearchViewHolder>() {
    private val keywords = mutableListOf<Keyword>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestSearchViewHolder {
        return TestSearchViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TestSearchViewHolder, position: Int) {
        holder.setData(keywords[position])
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    fun updateKeywords(keywordList:MutableList<Keyword>?){
        Timber.e(keywordList.toString())
        keywordList?.let { list->
            keywords.clear()
            keywords.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun removeAllItems(){
        keywords.clear()
        notifyDataSetChanged()
    }

}

