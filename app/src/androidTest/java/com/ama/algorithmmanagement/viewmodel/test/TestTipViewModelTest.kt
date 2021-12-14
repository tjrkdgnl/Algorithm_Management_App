package com.ama.algorithmmanagement.viewmodel.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ama.algorithmmanagement.fake.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class TestTipViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var testTipViewModel: TestTipViewModel

    @Before
    fun init() {
        val fakeSharedPreference = FakeSharedPreference(ApplicationProvider.getApplicationContext())

        fakeSharedPreference.setUserIdToLocal("skjh0818")
        fakeSharedPreference.setTierType(1)

        val fakeFirebaseReference = FakeFirebaseReference(
            ApplicationProvider.getApplicationContext(),
            FakeFirebaseDataProvider(), fakeSharedPreference
        )

        val fakeNetworkService = FakeNetworkService(FakeNetWorkDataProvider(fakeSharedPreference))

        testTipViewModel = TestTipViewModel(
            FakeRepository(fakeFirebaseReference, fakeNetworkService)
        )
    }

    @Test
    fun setTippingProblem() {
        


    }

}