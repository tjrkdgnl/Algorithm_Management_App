package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.IdeaInfo
import com.ama.algorithmmanagement.ViewHolder.test.TestIdeaViewHolder

class TestIdeaAdpater : RecyclerView.Adapter<TestIdeaViewHolder>() {
    private val lst = mutableListOf<IdeaInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestIdeaViewHolder {
        return TestIdeaViewHolder(parent)
    }

    override fun onBindViewHolder(holderTest: TestIdeaViewHolder, position: Int) {
        holderTest.setData(lst[position].comment)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(mutableList: MutableList<IdeaInfo>) {
        for (ideaInfo in mutableList) {
            if (!lst.contains(ideaInfo)) {
                lst.add(ideaInfo)
            }
        }
        notifyDataSetChanged()
    }
}