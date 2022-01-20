package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.ChildCommentInfo
import com.ama.algorithmmanagement.ViewHolder.test.TestChildCommentViewHolder

class TestChildCommentAdapter :
    RecyclerView.Adapter<TestChildCommentViewHolder>() {

    private val lst = mutableListOf<ChildCommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestChildCommentViewHolder {
        return TestChildCommentViewHolder(parent)
    }

    override fun onBindViewHolder(holderTest: TestChildCommentViewHolder, position: Int) {
        holderTest.bind(lst[position].comment)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(childCommentList: MutableList<ChildCommentInfo>?) {
        childCommentList?.let {
            lst.addAll(it)
        }
        notifyDataSetChanged()
    }
}