package com.ama.algorithmmanagement.Model

data class User(
    val background: Background,
    val badge: Badge,
    val bio: String,
    val `class`: Int,
    val classDecoration: String,
    val email: String,
    val exp: Int,
    val handle: String,
    val maxStreak: Int,
    val organizations: List<Organization>,
    val profileImageUrl: String,
    val rating: Int,
    val ratingByClass: Int,
    val ratingByProblemsSum: Int,
    val ratingBySolvedCount: Int,
    val ratingByVoteCount: Int,
    val reverseRivalCount: Int,
    val rivalCount: Int,
    val settings: Settings,
    val solvedCount: Int,
    val tier: Int,
    val voteCount: Int
)