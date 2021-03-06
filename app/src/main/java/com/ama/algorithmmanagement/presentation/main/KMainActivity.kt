package com.ama.algorithmmanagement.presentation.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.activity.kDefault.NewSolvedProblemActivity
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMainBinding
import com.ama.algorithmmanagement.domain.base.BaseViewModelFactory
import com.ama.algorithmmanagement.domain.base.KBaseActivity
import com.ama.algorithmmanagement.domain.entity.TipProblemInfo
import com.ama.algorithmmanagement.presentation.login.activity.KRLoginActivity
import com.ama.algorithmmanagement.presentation.main.adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.KUserDateInfoAdapter
import com.ama.algorithmmanagement.presentation.main.adapter.SolvedProblemStatsAdapter
import com.ama.algorithmmanagement.presentation.notip.NoTipActivity
import com.ama.algorithmmanagement.presentation.retryProblems.RetryProblemsInfoActivity
import com.ama.algorithmmanagement.presentation.retryProblems.adapter.RetryProblemsInfoAdapter
import com.ama.algorithmmanagement.presentation.search.SearchActivity
import com.ama.algorithmmanagement.presentation.setting.SettingActivity
import com.ama.algorithmmanagement.presentation.status.CategoryStatusActivity
import com.ama.algorithmmanagement.presentation.tryfailed.TryFailedActivity
import com.ama.algorithmmanagement.presentation.tryhistory.TryHistoryActivity
import com.ama.algorithmmanagement.presentation.vpdetail.activity.KViewProblemDetailActivity
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

        // drawer navigation ?????? ????????? ????????? ?????? ???????????? ??????
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
        // navigation header ??? ?????????(??????,?????????)
        // TODO : ?????? ????????? ???????????????????????? ????????????????????? ????????????
        val headerView = binding.mainView.getHeaderView(0)
        tvUserName = headerView.findViewById(R.id.tv_user_name)

        binding.appBarMain.contentMain.apply {
            viewmodel = mainViewModel
            // ????????? ?????? ?????????????????? ????????? ??????
            val statsProblemAdapter = SolvedProblemStatsAdapter()
            rvTypeStatics.adapter = statsProblemAdapter
            // ??????????????? ?????? ????????? ??????
            val retryProblemAdapter = RetryProblemsInfoAdapter()
            rvRetryProblem.adapter = retryProblemAdapter
            retryProblemAdapter.setOnItemClickListener(object :RetryProblemsInfoAdapter.OnRetryProblemClickListener{
                override fun onClick(v: View, data: TipProblemInfo) {
                    val builder = AlertDialog.Builder(this@KMainActivity)
                    builder.setItems(arrayOf("???????????? (????????? ????????????)","???????????? ????????????")
                    ) { _, p1 ->
                        when(p1){
                            0->{
                                val intent = Intent(applicationContext, KViewProblemDetailActivity::class.java)
                                intent.putExtra("problemId",data.problem?.problemId.toString())
                                startActivity(intent)
                            }
                            1->{
                                val intent = Intent(applicationContext, TryHistoryActivity::class.java)
                                intent.putExtra("problemId",data.problem?.problemId)
                                startActivity(intent)
                            }
                        }
                    }
                    builder.show()
                }
            })
            // ?????? ?????? ????????? ??????
            rvDateGrid.adapter = KUserDateInfoAdapter()
            position = 0 // ?????? ????????? ????????? ???????????? ?????????
            tiersTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    position = tab?.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            // refresh ?????? ????????? ?????? ??????
            swipeRefreshLayout.setOnRefreshListener {
                mainViewModel.fetchData()
                swipeRefreshLayout.isRefreshing = false
            }
            // scrollview ??? swipe refresh ?????? ???????????? ??????
            scrollView.viewTreeObserver.addOnScrollChangedListener {
                // ??????????????? ??? ????????? ???????????? ???????????? ??????
                swipeRefreshLayout.isEnabled = scrollView.scrollY == 0
            }

        }
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {
                mainViewModel.openDrawer()
            }
            override fun onDrawerClosed(drawerView: View) {
                // drawer ??????????????? ??????????????? ?????? ????????? ???????????? ?????????
                // ????????? ???????????? ????????? ????????? ???????????? ????????? ????????? ??????
                // ??????????????? ?????? drawer navigation ??? ??????????????? ????????? ???????????? ????????? ???????????????
                // drawer ??????????????? ????????? ??????????????????
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
        // ??????????????? ?????? ?????????
        mainViewModel.isOpenDrawer.observe(this) {
            Timber.e(it.toString())
            if (it) {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

        }
        // ???????????? ?????????
        mainViewModel.isClickSearchInput.observe(this) { isShow ->
            if (isShow) {
                val intent = Intent(this, SearchActivity::class.java)
                mainViewModel.initIsClickSearchInput() // ?????? ????????? isClickSearchInput ????????? ?????? ?????????
                startActivity(intent)
            }
        }
        // ?????? ??? ????????? ?????????
        mainViewModel.todaySolvedProblem.observe(this) { todaySolvedProblems->
            Timber.e(todaySolvedProblems.toString())
            if (todaySolvedProblems.size > 0) {
                val snackbar = Snackbar.make(
                    binding.appBarMain.contentMain.root,
                    "?????? ??? ????????? ??????????????????. ${todaySolvedProblems[0].titleKo} ??? ${todaySolvedProblems.size - 1} ??????",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("??? ??????????????????") {
                    snackbar.dismiss()
                    val intent = Intent(applicationContext,NewSolvedProblemActivity::class.java)

                    val todaySolvedProblemsId = ArrayList<Int>()
                    todaySolvedProblems?.map {
                        todaySolvedProblemsId.add(it.problemId)
                    }
                    Timber.e("today solved intent $todaySolvedProblems")
                    intent.putIntegerArrayListExtra("problems",todaySolvedProblemsId)

                    startActivity(intent)
                }
                snackbar.show()
            }

        }
        // ??????????????? ???????????? ?????? ?????????
        mainViewModel.isCategoryOpen.observe(this) {
            if (it) {
                val intent = Intent(applicationContext, CategoryStatusActivity::class.java)
                mainViewModel.initCategoryInfoAct()
                startActivity(intent)
            }
        }
        // ?????? ?????? ???????????????
        mainViewModel.userInfo.observe(this) {
            it?.let {
                Timber.e("$it ${it.tier}")
                val tierName = ColorUtils.intConvertToTier(it.tier)
                // TODO ????????? ?????? ????????? ????????????
                tvUserName.text = "$tierName   ${it.bio}"
            }
        }
        mainViewModel.isRetryProblemsInfo.observe(this){
            if(it){
                val intent = Intent(applicationContext, RetryProblemsInfoActivity::class.java)
                mainViewModel.initRetryProblemsInfo()
                startActivity(intent)
            }
        }

    }
}