package com.ama.algorithmmanagement.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Adapter.test.*
import com.ama.algorithmmanagement.model.*

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("setItemList")
    fun setItemList(recyclerView: RecyclerView, list: MutableList<KProblemsOfClass>?) {
        val defaultRecyclerViewAdapter = recyclerView.adapter as KDefaultRecyclerViewAdapter
        defaultRecyclerViewAdapter.updateList(list)
    }


    @JvmStatic
    @BindingAdapter("setTipProblemList")
    fun setSolvedProblemsList(
        recyclerView: RecyclerView,
        solvedAlgorithms: MutableList<TipProblemInfo>?
    ) {
        val recyclerViewAdapter = recyclerView.adapter as TestTipAdapter
        recyclerViewAdapter.updateList(solvedAlgorithms)
    }

    @JvmStatic
    @BindingAdapter("testIdeaProblems")
    fun setTestIdeaProblems(recyclerView: RecyclerView, problems: MutableList<IdeaInfo>?) {
        val adapter = recyclerView.adapter as TestIdeaAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("testCommentList")
    fun setTestCommentList(recyclerView: RecyclerView, problems: MutableList<CommentInfo>?) {
        val adapter = recyclerView.adapter as TestCommentAdapter

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
        val adapter = recyclerView.adapter as TestDateAdpater

        problems?.let {
            adapter.updateList(it)
        }
    }

}