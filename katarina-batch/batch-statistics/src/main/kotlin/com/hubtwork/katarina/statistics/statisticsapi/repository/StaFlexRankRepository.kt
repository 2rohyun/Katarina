package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaFlexRank
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StaFlexRankRepository: JpaRepository<StaFlexRank, Long> {
    @Query("select distinct champion_id from STA_flex_rank where summoner_name = :summoner_name and season = :season", nativeQuery = true)
    fun getUniqueChampionIdBySummonerNameAndSeason(@Param("summoner_name")summoner_name: String,
                                                   @Param("season")season: Int) : MutableList<Int>

    @Query("select * from STA_flex_rank where champion_id = :champion_id",nativeQuery = true)
    fun getAllByChampionId(@Param("champion_id")champion_id: Int): MutableList<StaFlexRank>
}