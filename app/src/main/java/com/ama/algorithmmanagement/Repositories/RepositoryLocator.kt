package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Network.BaseNetworkService
import com.ama.algorithmmanagement.fake.*

class RepositoryLocator {

    private lateinit var mRepository: Repository
    private lateinit var mFakeRepository: FakeRepository


    fun getRepository(): Repository {
        if (!this::mRepository.isInitialized) {
            mRepository = Repository()
        }
        return mRepository
    }

    fun getFakeRepository(app: Application): FakeRepository {
        if (!this::mFakeRepository.isInitialized) {
            val firebaseReference =
                FakeFirebaseReference(app, FakeFirebaseDataProvider(), FakeSharedPreference())
            val networkService = FakeNetworkService(FakeNetWorkDataProvider())

            mFakeRepository = FakeRepository(firebaseReference, networkService)
        }

        return mFakeRepository
    }

}