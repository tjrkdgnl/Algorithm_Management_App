package com.ama.algorithmmanagement.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.activity.kDefault.SearchActivity
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMainBinding
import com.ama.algorithmmanagement.presentation.main.adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.KUserDateInfoAdapter
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class KMainActivity : KBaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewModel: KMainViewModel
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KMainViewModel::class.java]

        drawerLayout = binding.drawerLayout
        binding.appBarMain.contentMain.apply {
            rvRetryProblem.adapter = KRetryProblemsAdapter()
            rvDateGrid.adapter = KUserDateInfoAdapter()
            position = 0 // 처음 보여줄 그래프는 브론즈
            tiersTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    position = tab?.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

        }
        mainViewModel.isOpenDrawer.observe(this@KMainActivity) {
            Timber.e(it.toString())
            if (it) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }

        }
        mainViewModel.isClickSearchInput.observe(this) { isShow ->
            if (isShow) {
                Toast.makeText(this, "검색화면으로 전환", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@KMainActivity, SearchActivity::class.java)
                mainViewModel.initIsClickSearchInput() // 화면 전환후 isClickSearchInput 에대한 값은 초기화
                startActivity(intent)
            }
        }
        drawerLayout.addDrawerListener(object:DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
                mainViewModel.openDrawer()
            }

            override fun onDrawerClosed(drawerView: View) {
                // drawer 레이아웃을 작동시킬수 있는 방법은 두가지가 있는데
                // 화면을 슬라이드 하거나 버튼을 클릭하는 두가지 방법이 있음
                // 슬라이드를 통해 drawer navigation 을 작동시키면 뷰모델 상태값이 변하지 않기떄문에
                // drawer 리스너에서 상태를 변경해줘야됨
                mainViewModel.closeDrawer()
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })


    }
}