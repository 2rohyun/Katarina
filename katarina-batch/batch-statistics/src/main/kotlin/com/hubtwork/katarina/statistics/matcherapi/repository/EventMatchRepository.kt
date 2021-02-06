package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.EventMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventMatchRepository: JpaRepository<EventMatch, Long>{
    @Query("drop table eventmatch",nativeQuery = true)
    fun dropEventMatch()
}