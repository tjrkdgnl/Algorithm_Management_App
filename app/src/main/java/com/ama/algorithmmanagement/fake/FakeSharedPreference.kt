package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.Model.Problems
import com.google.gson.Gson
import kotlin.collections.HashMap

class FakeSharedPreference(private val mApp: Application) : BaseSharedPreference {
    private val fakeMap = HashMap<SharedKey, Any>()

    override fun setUserIdToLocal(userId: String) {
        fakeMap[SharedKey.USERID] = userId
    }

    override fun getUserIdFromLocal(): String? {
        return fakeMap[SharedKey.USERID] as? String
    }

    override fun deleteToUserId() {
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

    override fun setSolvedProblems(solvedProblem: Problems) {
        if (!fakeMap.containsKey(SharedKey.SOLVED)) {
            val gson = Gson()
            val jsonObj = gson.toJson(solvedProblem)

            fakeMap[SharedKey.SOLVED] = jsonObj
        }
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
        SOLVED, USERID, TIER, TOKEN
    }

}