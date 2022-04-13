package com.ama.algorithmmanagement.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMainBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.presentation.login.activity.KRLoginActivity
import com.ama.algorithmmanagement.presentation.main.adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.KUserDateInfoAdapter
import com.ama.algorithmmanagement.presentation.notip.NoTipActivity
import com.ama.algorithmmanagement.presentation.search.SearchActivity
import com.ama.algorithmmanagement.presentation.setting.SettingActivity
import com.ama.algorithmmanagement.presentation.status.CategoryStatusActivity
import com.ama.algorithmmanagement.presentation.tryfailed.TryFailedActivity
import com.ama.algorithmmanagement.utils.ColorUtils
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class KMainActivity : KBaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewModel: KMainViewModel
    private lateinit var tvUserName:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(
            this,
            BaseViewModelFactory(RepositoryLocator().getRepository(AMAApplication.INSTANCE))
        )[KMainViewModel::class.java]

        // drawer navigation 에서 아이템 클릭시 해당 화면으로 이동
        binding.mainView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_no_tip -> {
                    val intent = Intent(applicationContext, NoTipActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_try_but_fail -> {
                    val intent = Intent(applicationContext, TryFailedActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_setting -> {
                    val intent = Intent(applicationContext, SettingActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> true
            }
        }
        // navigation header 뷰 가져옴(티어,닉네임)
        // TODO : 해당 부분은 데이터바인딩으로 처리할수있는지 알아보기
        val headerView = binding.mainView.getHeaderView(0)
        tvUserName = headerView.findViewById(R.id.tv_user_name)

        binding.appBarMain.contentMain.apply {
            viewmodel = mainViewModel
            // 다시풀어볼 문제 어댑터 세팅
            rvRetryProblem.adapter = KRetryProblemsAdapter()
            // 월별 통계 어댑터 세팅
            rvDateGrid.adapter = KUserDateInfoAdapter()
            position = 0 // 처음 보여줄 티어별 그래프는 브론즈
            tiersTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    position = tab?.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            // refresh 할때 데이터 다시 세팅
            swipeRefreshLayout.setOnRefreshListener {
                mainViewModel.fetchData()
                swipeRefreshLayout.isRefreshing = false
            }
            // scrollview 와 swipe refresh 같이 쓰기위한 처리
            scrollView.viewTreeObserver.addOnScrollChangedListener {
                // 스크롤뷰가 최 상단에 있을때만 스와이프 작동
                swipeRefreshLayout.isEnabled = scrollView.scrollY == 0
            }

        }
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
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
            override fun onDrawerStateChanged(newState: Int) {}
        })
        setObserver()
    }

    override fun onStart() {
        super.onStart()
        // check login status
        if (!mainViewModel.isLogin()) {
            startActivity(Intent(this, KRLoginActivity::class.java))
            finish()
        }
    }

    private fun setObserver(){
        // 네비게이션 버튼 누를시
        mainViewModel.isOpenDrawer.observe(this) {
            Timber.e(it.toString())
            if (it) {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

        }
        // 검색화면 누를시
        mainViewModel.isClickSearchInput.observe(this) { isShow ->
            if (isShow) {
                Toast.makeText(this, "검색화면으로 전환", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SearchActivity::class.java)
                mainViewModel.initIsClickSearchInput() // 화면 전환후 isClickSearchInput 에대한 값은 초기화
                startActivity(intent)
            }
        }
        // 오늘 푼 문제가 있을시
        mainViewModel.todaySolvedProblem.observe(this) {
            Timber.e(it.toString())
            if (it.size > 0) {
                val snackbar = Snackbar.make(
                    binding.appBarMain.contentMain.root,
                    "새로 푼 문제가 감지됬습니다. ${it[0].titleKo} 외 ${it.size - 1} 문제",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("팁 작성하러가기") {
                    snackbar.dismiss()
                }
//                val intent = Intent(applicationContext,NewSolvedProblemActivity::class.java)
//                startActivity(intent)
                snackbar.show()
            }

        }
        // 유형별통계 상세보기 버튼 누를시
        mainViewModel.isCategoryOpen.observe(this) {
            if (it) {
                val intent = Intent(applicationContext, CategoryStatusActivity::class.java)
                mainViewModel.initCategoryInfoAct()
                startActivity(intent)
            }
        }
        // 유저 정보 불러왔을때
        mainViewModel.userInfo.observe(this) {
            it?.let {
                Timber.e("$it ${it.tier}")
                val tierName = ColorUtils.intConvertToTier(it.tier)
                // TODO 티어에 맞는 이미지 보여주기
                tvUserName.text = "$tierName   ${it.bio}"
            }
        }

    }
}