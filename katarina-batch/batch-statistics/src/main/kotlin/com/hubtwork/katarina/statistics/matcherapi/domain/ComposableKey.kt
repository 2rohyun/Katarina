package com.hubtwork.katarina.statistics.matcherapi.domain

import java.io.Serializable

class ComposableKey(
    val accountId: String = "",
    val matchId: Long = 0
): Serializable