package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils

class RepositoryLocator {

    private lateinit var mRepository: Repository
    private lateinit var mFakeRepository: FakeRepository

    fun getRepository(app: Application): Repository {
        if (!this::mRepository.isInitialized) {
            mRepository =
                Repository(
                    app,
                    FirebaseService(app),
                    FakeNetworkService(FakeNetWorkDataProvider()),
                    FakeSharedPreference()
                )
        }
        return mRepository
    }

    fun getFakeRepository(app: Application): FakeRepository {
        if (!this::mFakeRepository.isInitialized) {
            val firebaseReference =
                FakeFirebaseReference(FakeFirebaseDataProvider(), DateUtils.createDate())
            val networkService = FakeNetworkService(FakeNetWorkDataProvider())

            mFakeRepository =
                FakeRepository(app, firebaseReference, networkService, FakeSharedPreference())
        }

        return mFakeRepository
    }

}