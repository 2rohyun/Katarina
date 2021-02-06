package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.MatchData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MatchDataRepository: JpaRepository<MatchData, Long>{
    @Query("drop table matches",nativeQuery = true)
    fun dropMatchData()
}