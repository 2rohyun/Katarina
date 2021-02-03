package com.hubtwork.katarina.batchmatch.api.repository

import com.hubtwork.katarina.batchmatch.api.domain.SoloRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SoloRankRepository: JpaRepository<SoloRank, Long> {

    @Query("select * from ranksolo limit 1 ", nativeQuery = true)
    fun getFirstMatch(): SoloRank?
}