package com.hubtwork.katarina.statistics.api.domain

import java.io.Serializable

class ComposableKey(
    val accountId: String = "",
    val championId: Int = 0
): Serializable