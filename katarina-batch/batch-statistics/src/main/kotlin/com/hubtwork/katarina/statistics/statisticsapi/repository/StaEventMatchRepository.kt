package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaEventMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StaEventMatchRepository: JpaRepository<StaEventMatch, Long> {
}