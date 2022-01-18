package com.ama.algorithmmanagement.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Adapter.KSolvedProblemsAdapter
import com.ama.algorithmmanagement.Adapter.test.CommentAdapter
import com.ama.algorithmmanagement.Adapter.test.DateAdpater
import com.ama.algorithmmanagement.Adapter.test.IdeaAdpater
import com.ama.algorithmmanagement.Adapter.test.TestChildCommentAdapter
import com.ama.algorithmmanagement.Model.*

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("setItemList")
    fun setItemList(recyclerView: RecyclerView, list: MutableList<KProblemsOfClass>?) {
        val defaultRecyclerViewAdapter = recyclerView.adapter as KDefaultRecyclerViewAdapter
        defaultRecyclerViewAdapter.updateList(list)
    }


    @JvmStatic
    @BindingAdapter("setSolvedProblemsList")
    fun setSolvedProblemsList(
        recyclerView: RecyclerView,
        solvedAlgorithms: MutableList<TaggedProblem>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as KSolvedProblemsAdapter
        recyclerViewAdapter.updateList(solvedAlgorithms)
    }

    @JvmStatic
    @BindingAdapter("testIdeaProblems")
    fun setTestIdeaProblems(recyclerView: RecyclerView, problems: MutableList<IdeaInfo>?) {
        val adapter = recyclerView.adapter as IdeaAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("testCommentList")
    fun setTestCommentList(recyclerView: RecyclerView, problems: MutableList<CommentInfo>?) {
        val adapter = recyclerView.adapter as CommentAdapter

        problems?.let {
            adapter.updateList(it)
        }
    }


    @JvmStatic
    @BindingAdapter("testChildCommentList")
    fun setTestChildCommentList(recyclerView: RecyclerView, problems: MutableList<ChildCommentInfo>?) {
        val adapter = recyclerView.adapter as TestChildCommentAdapter

        problems?.let {
            adapter.updateList(it)
        }
    }


    @JvmStatic
    @BindingAdapter("testDateList")
    fun setTestDateList(recyclerView: RecyclerView, problems: MutableList<DateInfo>?) {
        val adapter = recyclerView.adapter as DateAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

}