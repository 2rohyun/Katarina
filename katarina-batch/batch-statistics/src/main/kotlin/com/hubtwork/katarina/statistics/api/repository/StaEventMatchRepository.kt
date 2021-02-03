package com.hubtwork.katarina.statistics.api.repository

import com.hubtwork.katarina.statistics.api.domain.StaComposableKey
import com.hubtwork.katarina.statistics.api.domain.StaEventMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StaEventMatchRepository: JpaRepository<StaEventMatch, StaComposableKey> {
}