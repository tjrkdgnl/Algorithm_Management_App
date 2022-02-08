package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.CommentInfo
import com.ama.algorithmmanagement.ViewHolder.test.TestCommentViewHolder

class TestCommentAdapter(private val moveToChild: (String) -> Unit) :
    RecyclerView.Adapter<TestCommentViewHolder>() {
    private val lst = mutableListOf<CommentInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestCommentViewHolder {
        return TestCommentViewHolder(parent, moveToChild)
    }

    override fun onBindViewHolder(holderTest: TestCommentViewHolder, position: Int) {
        holderTest.bind(lst[position].comment)
        holderTest.setCommentId(lst[position].commentId)
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