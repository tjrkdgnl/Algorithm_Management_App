package com.ama.algorithmmanagement.utils

import android.content.Context
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.model.Problems
import com.google.gson.Gson

class SharedPrefUtils(private val mContext: Context) : BaseSharedPreference {
    private val mSharedPreferences = mContext.getSharedPreferences("AMA_pref", Context.MODE_PRIVATE)
    private val edit = mSharedPreferences.edit()

    override fun setUserId(userId: String) {
        edit.putString(SharedKey.USERID.name, userId).apply()
    }

    override fun getUserId(): String? {
        return mSharedPreferences.getString(SharedKey.USERID.name, null)
    }

    override fun deleteUserId() {
        edit.remove(SharedKey.USERID.name).apply()
    }

    override fun setAutoLoginCheck(check: Boolean) {
        edit.putBoolean(SharedKey.AUTO_LOGIN.name, check).apply()
    }

    override fun getAutoLoginCheck(): Boolean {
        return mSharedPreferences.getBoolean(SharedKey.AUTO_LOGIN.name, false)
    }

    override fun deleteAutoLoginCheck() {
        edit.remove(SharedKey.AUTO_LOGIN.name).apply()
    }

    override fun setTierType(tierType: Int) {
        edit.putInt(SharedKey.TIER.name, tierType).apply()
    }

    override fun getTierType(): Int {
        return mSharedPreferences.getInt(SharedKey.TIER.name, -1)
    }

    override fun deleteToTierType() {
        edit.remove(SharedKey.TIER.name).apply()
    }

    override fun setSolvedacToken(solvedacToken: String) {
        edit.putString(SharedKey.TOKEN.name, solvedacToken).apply()
    }

    override fun getSolvedacToken(): String? {
        return mSharedPreferences.getString(SharedKey.TOKEN.name, null)
    }

    override fun deleteSolvedacToken() {
        edit.remove(SharedKey.TOKEN.name).apply()
    }

    override fun setSolvedProblems(solvedProblems: Problems) {
        with(Gson()) {
            val json = toJson(solvedProblems)
            edit.putString(SharedKey.SOLVED.name, json).apply()
        }
    }

    override fun getSolvedProblems(): Problems? {
        return with(Gson()) {
            val json = mSharedPreferences.getString(SharedKey.SOLVED.name, null)

            if (json != null) {
                return@with fromJson(json, Problems::class.java)
            } else {
                null
            }
        }
    }

    override fun deleteSolvedProblems() {
        edit.remove(SharedKey.SOLVED.name).apply()
    }

    enum class SharedKey {
        SOLVED, USERID, TIER, TOKEN, AUTO_LOGIN
    }

}