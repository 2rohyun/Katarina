package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.ARAM
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ARAMRepository: JpaRepository<ARAM, Long>{

    @Query("drop table ARAM",nativeQuery = true)
    fun dropARAM()
}