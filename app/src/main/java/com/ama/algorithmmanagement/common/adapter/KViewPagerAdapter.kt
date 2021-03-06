package com.ama.algorithmmanagement.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import timber.log.Timber

/**
 * author : manyong Han
 * summary : 뷰페이져 어댑터 (회원가입화면, 코멘트화면 사용)
 */

class KViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragments: List<Fragment>,
    lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        Timber.e("position : $position, 호출 프래그먼트 : ${fragments[position]}")
        return fragments[position]
    }
}