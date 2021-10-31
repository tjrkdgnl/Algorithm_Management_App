package com.ama.algorithmmanagement.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.Adapter.KDefaultRecyclerViewAdapter
import com.ama.algorithmmanagement.Adapter.KSolvedProblemsAdapter
import com.ama.algorithmmanagement.Model.KProblemsOfClass
import com.ama.algorithmmanagement.Model.Problem

object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("setItemList")
    fun setItemList(recyclerView: RecyclerView, list: MutableList<KProblemsOfClass>?) {
        val defaultRecyclerViewAdapter = recyclerView.adapter as KDefaultRecyclerViewAdapter
        defaultRecyclerViewAdapter.updateList(list)
    }


    @JvmStatic
    @BindingAdapter("setSolvedProblemsList")
    fun setSolvedProblemsList(recyclerView: RecyclerView, solvedAlgorithms: MutableList<Problem>?) {
        val recyclerViewAdapter = recyclerView.adapter as KSolvedProblemsAdapter
        recyclerViewAdapter.updateList(solvedAlgorithms)
    }

}