package com.ama.algorithmmanagement.data.repositories

import android.app.Application
import com.ama.algorithmmanagement.application.AMAApplication
import com.ama.algorithmmanagement.data.fake.*
import com.ama.algorithmmanagement.data.firebase.FirebaseService
import com.ama.algorithmmanagement.data.network.NetworkService
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class RepositoryLocator {

    private lateinit var mRepositoryImpl: RepositoryImpl
    private lateinit var mFakeRepository: FakeRepository

    fun getRepository(app: Application): RepositoryImpl {
        if (!this::mRepositoryImpl.isInitialized) {
            mRepositoryImpl =
                RepositoryImpl(
                    app,
                    FirebaseService(app),
                    NetworkService(),
                    AMAApplication.INSTANCE.sharedPrefUtils
                )
        }
        return mRepositoryImpl
    }

    fun getFakeRepository(app: Application): FakeRepository {
        if (!this::mFakeRepository.isInitialized) {
            val firebaseReference =
                FakeFirebaseReference(FakeFirebaseDataProvider(app))
            val networkService = FakeNetworkService(FakeNetWorkDataProvider(app))

            mFakeRepository =
                FakeRepository(app, firebaseReference, networkService, FakeSharedPreference())
        }

        return mFakeRepository
    }

}