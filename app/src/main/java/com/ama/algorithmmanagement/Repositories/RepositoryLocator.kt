package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.fake.FakeRepository

object RepositoryLocator {

    lateinit var mRepository: Repository

    lateinit var mFakeRepository: FakeRepository


    fun getRepository(): Repository {
        if (this::mRepository.isInitialized) {
            mRepository = Repository()
        }
        return mRepository
    }

    fun getFakeRepository(app: Application): FakeRepository {
        if (this::mFakeRepository.isInitialized) {
            mFakeRepository = FakeRepository(app)
        }

        return mFakeRepository
    }

}