package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StaNormalMatchRepository: JpaRepository<StaNormalMatch, Long> {
}