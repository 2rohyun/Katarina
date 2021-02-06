package com.hubtwork.katarina.statistics.matcherapi.repository

import com.google.gson.annotations.JsonAdapter
import com.hubtwork.katarina.statistics.matcherapi.domain.SoloRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SoloRankRepository: JpaRepository<SoloRank, Long> {

    @Query("select * from ranksolo limit 1 ", nativeQuery = true)
    fun getFirstMatch(): SoloRank?

    @Query("select matchJson from ranksolo", nativeQuery = true)
    fun getAllMatchJson(): String

    @Query("drop table ranksolo",nativeQuery = true)
    fun dropSoloRank()
}