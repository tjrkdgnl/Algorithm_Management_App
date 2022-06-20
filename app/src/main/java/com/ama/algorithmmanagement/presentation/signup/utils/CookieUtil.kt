package com.ama.algorithmmanagement.presentation.signup.utils

import android.webkit.CookieManager

object CookieUtil {
    fun extractToken(url: String): String? {
        val cookieString = CookieManager.getInstance().getCookie(url) ?: return null

        val lst = cookieString.split(";").filter { cookie ->
            cookie.isNotBlank()
        }.map {
            val data = it.split("=")
            data[0].trim() to data[1].trim()
        }

        lst.forEach { cookiePair ->
            if (cookiePair.first == "solvedacToken") {
                return "id=" + cookiePair.second
            }
        }

        return null
    }
}