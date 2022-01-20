package com.ama.algorithmmanagement.model

data class Settings(
    val iconSchemeNotSolved: String,
    val iconSchemeSolved: String,
    val problemSortBy: String,
    val screenTheme: String,
    val tagDisplayLanguage: String,
    val twitterPostHandle: String,
    val twitterPostOnClassIncrease: String,
    val twitterPostOnProblemSolve: String,
    val twitterPostOnRatingIncrease: String,
    val twitterPostOnTierIncrease: String
)