package com.ama.algorithmmanagement.adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.viewHolder.test.TestCommentViewHolder

class TestCommentAdapter(private val moveToChild: (CommentInfo) -> Unit) :
    RecyclerView.Adapter<TestCommentViewHolder>() {
    private val lst = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestCommentViewHolder {
        return TestCommentViewHolder(parent, moveToChild)
    }

    override fun onBindViewHolder(holderTest: TestCommentViewHolder, position: Int) {
        holderTest.bind(lst[position].comment)
        holderTest.setCommentInfo(lst[position])
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(commentList: MutableList<CommentInfo>?) {
        commentList?.let {
            lst.addAll(it)
        }
        notifyDataSetChanged()
    }
}