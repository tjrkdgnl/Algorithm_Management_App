package com.ama.algorithmmanagement.Activity.kDefault

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.ama.algorithmmanagement.Adapter.KRetryProblemsAdapter
import com.ama.algorithmmanagement.Adapter.KUserDateInfoAdapter
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseViewModelFactory
import com.ama.algorithmmanagement.Base.KBaseActivity
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.Repositories.RepositoryLocator
import com.ama.algorithmmanagement.databinding.ActivityMainBinding
import com.ama.algorithmmanagement.utils.TierColors
import com.ama.algorithmmanagement.viewmodel.kDefault.KMainViewModel
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
            viewmodel = mainViewModel
            rvRetryProblem.adapter = KRetryProblemsAdapter()
            rvDateGrid.adapter = KUserDateInfoAdapter()
            position = TierColors.BRONZE
            tiersTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.appBarMain.contentMain.position = tab?.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

        }
        mainViewModel.isOpenDrawer.observe(this@KMainActivity, {
            Timber.e(it.toString())
            if(it){
                drawerLayout.openDrawer(GravityCompat.START)
            }else{
                drawerLayout.closeDrawer(GravityCompat.START)
            }

        })
        drawerLayout.addDrawerListener(object:DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                mainViewModel.toggleOpenDrawer()
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })


    }
}