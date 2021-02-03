package com.hubtwork.katarina.statistics.api.repository

import com.hubtwork.katarina.statistics.api.domain.StaARAM
import com.hubtwork.katarina.statistics.api.domain.StaComposableKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StaARAMRepository: JpaRepository<StaARAM, StaComposableKey> {
}