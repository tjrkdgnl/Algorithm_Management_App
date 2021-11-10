package com.ama.algorithmmanagement.Repositories

object RepositoryLocator {

    private val repository = Repository()

    private val fakeRepository = FakeRepository()



    fun getRepository() = repository
    fun getFakeRepository() = fakeRepository

}