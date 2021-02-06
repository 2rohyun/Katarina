package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.FlexRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlexRankRepository: JpaRepository<FlexRank, Long>{
    @Query("drop table rankflex",nativeQuery = true)
    fun dropFlexRank()
}