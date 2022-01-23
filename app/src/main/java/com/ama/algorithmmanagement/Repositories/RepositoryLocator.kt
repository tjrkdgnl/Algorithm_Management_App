package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class RepositoryLocator {

    private lateinit var mRepository: Repository
    private lateinit var mFakeRepository: FakeRepository

    fun getRepository(app: Application): Repository {
        if (!this::mRepository.isInitialized) {
            mRepository =
                Repository(
                    app,
                    FirebaseService(app),
                    NetworkService(),
                    AMAApplication.INSTANCE.sharedPrefUtils
                )
        }
        return mRepository
    }

    fun getFakeRepository(app: Application): FakeRepository {
        if (!this::mFakeRepository.isInitialized) {
            val firebaseReference =
                FakeFirebaseReference(FakeFirebaseDataProvider(app), DateUtils.createDate())
            val networkService = FakeNetworkService(FakeNetWorkDataProvider(app))

            mFakeRepository =
                FakeRepository(app, firebaseReference, networkService, FakeSharedPreference())
        }

        return mFakeRepository
    }

}