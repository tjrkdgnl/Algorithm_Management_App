package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.fake.FakeRepository

object RepositoryLocator {

    private val repository = Repository()

    private val fakeRepository = FakeRepository()



    fun getRepository() = repository
    fun getFakeRepository() = fakeRepository

}