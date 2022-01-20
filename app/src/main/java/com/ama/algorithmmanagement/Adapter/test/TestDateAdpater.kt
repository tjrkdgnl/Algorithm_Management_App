package com.ama.algorithmmanagement.Adapter.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ama.algorithmmanagement.model.DateInfo
import com.ama.algorithmmanagement.ViewHolder.test.TestDateViewHolder

class TestDateAdpater : RecyclerView.Adapter<TestDateViewHolder>() {
    private val lst = mutableListOf<DateInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestDateViewHolder {
        return TestDateViewHolder(parent)
    }

    override fun onBindViewHolder(holderTest: TestDateViewHolder, position: Int) {
        holderTest.setData(lst[position].date)
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun updateList(mutableList: MutableList<DateInfo>) {
        for (dateInfo in mutableList) {
            if (!lst.contains(dateInfo)) {
                lst.add(dateInfo)
            }
        }
        notifyDataSetChanged()
    }
}