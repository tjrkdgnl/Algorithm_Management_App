package com.ama.algorithmmanagement.adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.TipProblemInfo
import com.ama.algorithmmanagement.viewHolder.test.TestTipViewHolder

class TestTipAdapter(
    private val moveToWriteTip: (TipProblemInfo) -> Unit
) : RecyclerView.Adapter<TestTipViewHolder>() {

    private val lst = mutableListOf<TipProblemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestTipViewHolder {
        return TestTipViewHolder(parent, moveToWriteTip)
    }

    override fun onBindViewHolder(holderTest: TestTipViewHolder, position: Int) {
        holderTest.bind(lst[position])
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(problems: List<TipProblemInfo>?) {
        problems?.let {
            lst.addAll(it)
            notifyDataSetChanged()
        }
    }
}