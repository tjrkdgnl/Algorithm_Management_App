package com.ama.algorithmmanagement.adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.viewHolder.test.TestIdeaViewHolder
import com.ama.algorithmmanagement.data.model.IdeaInfo
import timber.log.Timber

class TestIdeaAdpater : RecyclerView.Adapter<TestIdeaViewHolder>() {
    private val lst = mutableListOf<IdeaInfo?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestIdeaViewHolder {
        return TestIdeaViewHolder(parent)
    }

    override fun onBindViewHolder(holderTest: TestIdeaViewHolder, position: Int) {
        holderTest.setData(lst[position]?.comment)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(mutableList: MutableList<IdeaInfo>) {
        Timber.d("여기 안오냐 $mutableList")
        for (ideaInfo in mutableList) {
            if (!lst.contains(ideaInfo)) {
                lst.add(ideaInfo)
            }
        }
        notifyDataSetChanged()
    }
}