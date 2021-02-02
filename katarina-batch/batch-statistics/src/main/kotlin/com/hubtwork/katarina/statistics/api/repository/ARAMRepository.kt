package com.hubtwork.katarina.statistics.api.repository

import com.hubtwork.katarina.statistics.api.domain.ARAM
import com.hubtwork.katarina.statistics.api.domain.ComposableKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ARAMRepository: JpaRepository<ARAM, ComposableKey> {
}