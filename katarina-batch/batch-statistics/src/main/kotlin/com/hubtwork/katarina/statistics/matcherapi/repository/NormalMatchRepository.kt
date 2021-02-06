package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.NormalMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface NormalMatchRepository: JpaRepository<NormalMatch, Long>{
    @Query("drop table normaldraft",nativeQuery = true)
    fun dropNormalMatch()
}