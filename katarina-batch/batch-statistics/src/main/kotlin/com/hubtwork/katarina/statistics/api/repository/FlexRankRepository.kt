package com.hubtwork.katarina.statistics.api.repository

import com.hubtwork.katarina.statistics.api.domain.ComposableKey
import com.hubtwork.katarina.statistics.api.domain.FlexRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlexRankRepository: JpaRepository<FlexRank, ComposableKey> {
}