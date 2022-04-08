package com.ama.algorithmmanagement.data.fake

import com.ama.algorithmmanagement.domain.base.BaseSharedPreference
import com.ama.algorithmmanagement.domain.entity.Problems
import com.google.gson.Gson

class FakeSharedPreference : BaseSharedPreference {
    private val fakeMap = HashMap<SharedKey, Any>()

    override fun setUserId(userId: String) {
        fakeMap[SharedKey.USERID] = userId
    }

    override fun getUserId(): String? {
        return fakeMap[SharedKey.USERID] as? String
    }

    override fun setAutoLoginCheck(check: Boolean) {
        fakeMap[SharedKey.AUTO_LOGIN] = check
    }

    override fun getAutoLoginCheck(): Boolean {
        val check = fakeMap[SharedKey.AUTO_LOGIN] as? Boolean
        return check ?: false
    }

    override fun deleteAutoLoginCheck() {
        fakeMap.remove(SharedKey.AUTO_LOGIN)
    }

    override fun deleteUserId() {
        fakeMap.remove(SharedKey.USERID)
    }

    override fun setTierType(tierType: Int) {
        fakeMap[SharedKey.TIER] = tierType
    }

    override fun getTierType(): Int? {
        return fakeMap[SharedKey.TIER] as? Int
    }

    override fun deleteToTierType() {
        fakeMap.remove(SharedKey.TIER)
    }

    override fun setSolvedacToken(solvedacToken: String) {
        fakeMap[SharedKey.TOKEN] = solvedacToken
    }

    override fun getSolvedacToken(): String? {
        return fakeMap[SharedKey.TOKEN] as? String
    }

    override fun deleteSolvedacToken() {
        fakeMap.remove(SharedKey.TOKEN)
    }

    override fun setSolvedProblems(solvedProblems: Problems) {
        val gson = Gson()
        val jsonObj = gson.toJson(solvedProblems)

        fakeMap[SharedKey.SOLVED] = jsonObj
    }

    override fun getSolvedProblems(): Problems? {
        val jsonObj = fakeMap[SharedKey.SOLVED] as? String
        val gson = Gson()

        return gson.fromJson(jsonObj, Problems::class.java)
    }

    override fun deleteSolvedProblems() {
        fakeMap.remove(SharedKey.SOLVED)
    }

    enum class SharedKey {
        SOLVED, USERID, TIER, TOKEN, AUTO_LOGIN
    }

}