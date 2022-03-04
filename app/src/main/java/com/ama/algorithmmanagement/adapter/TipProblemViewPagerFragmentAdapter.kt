package com.ama.algorithmmanagement.adapter;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author SeungHo Lee
 * summary : viewpager2 프레그먼트 어댑터
 */
class TipProblemViewPagerFragmentAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    private val mFragments  = mutableListOf<Fragment>()
    override fun getItemCount(): Int = mFragments.size

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }

    fun addFragment(fragment:Fragment){
        mFragments.add(fragment)
        notifyItemInserted(mFragments.size-1)
    }
    fun updateFragment(fragments:MutableList<Fragment>){
        mFragments.addAll(fragments)
        notifyDataSetChanged()
    }

}