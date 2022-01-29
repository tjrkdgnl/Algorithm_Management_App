package com.ama.algorithmmanagement.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Adapter.test.*
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.Adapter.KNoTipProblemsAdapter
import com.ama.algorithmmanagement.Adapter.TryFailedAdapter
import com.ama.algorithmmanagement.Adapter.CommentListAdapter
import timber.log.Timber


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

    @JvmStatic
    @BindingAdapter("setNoTipProblemsList")
    fun setNoTipProblemsList(recyclerView: RecyclerView, noTipProblems: MutableList<TipProblemInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as KNoTipProblemsAdapter
        recyclerViewAdapter.updateList(noTipProblems)
    }

    @JvmStatic
    @BindingAdapter("setTryFailedProblemsList")
    fun setTryFailedProblemsList(recyclerView: RecyclerView, tryFailedList: MutableList<TaggedProblem>?) {
        val recyclerViewAdapter = recyclerView.adapter as TryFailedAdapter
        recyclerViewAdapter.updateList(tryFailedList)
    }

    @JvmStatic
    @BindingAdapter("setMyCommentList")
    fun setMyCommentList(recyclerView: RecyclerView, commentInfo: MutableList<CommentInfo>?) {
        val recyclerViewAdapter = recyclerView.adapter as CommentListAdapter
//        recyclerViewAdapter.updateList(commentInfo)

        Timber.e("hongchul" + commentInfo.toString())

        commentInfo?.let {
            recyclerViewAdapter.updateList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setMyIdeaList")
    fun setMyIdeaList(recyclerView: RecyclerView, ideaInfo: MutableList<IdeaInfo>) {
        val recyclerViewAdapter = recyclerView.adapter as TestIdeaAdpater
        recyclerViewAdapter.updateList(ideaInfo)
    }

}