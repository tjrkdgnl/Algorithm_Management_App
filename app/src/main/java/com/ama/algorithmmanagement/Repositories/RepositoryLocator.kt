package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.fake.*
import com.ama.algorithmmanagement.utils.DateUtils

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
                FakeFirebaseReference(FakeFirebaseDataProvider(app), DateUtils.createDate())
            val networkService = FakeNetworkService(FakeNetWorkDataProvider(app))

            mFakeRepository =
                FakeRepository(app, firebaseReference, networkService, FakeSharedPreference())
        }

        return mFakeRepository
    }

}