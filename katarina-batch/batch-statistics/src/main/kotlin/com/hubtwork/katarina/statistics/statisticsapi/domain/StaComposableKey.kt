package com.hubtwork.katarina.statistics.statisticsapi.domain

import java.io.Serializable

class StaComposableKey(
    val accountId: String = "",
    val championId: Int = 0,
    val season: Int = 0
): Serializable