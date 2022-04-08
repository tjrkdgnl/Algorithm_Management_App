package com.ama.algorithmmanagement.utils

import java.util.*


object RandomIdGenerator {
    private val PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz"
    private val randGen = Random()
    private val lastRandChars = IntArray(12)

    fun generateRandDomId(): String {
        val currentTime = System.currentTimeMillis()

        val timeStampChars = StringBuffer(8)
        val result = StringBuilder(20)
        var now = 10

        for (i in 0 until 7) {
            timeStampChars.append(currentTime % now)
            now *= 10
        }
        timeStampChars.reverse()

        result.append(timeStampChars)

        for (i in 0 until 12) {
            lastRandChars[i] = randGen.nextInt(64)
        }

        for (i in 0 until 12) {
            result.append(PUSH_CHARS[lastRandChars[i]])
        }

        return result.toString()
    }

}