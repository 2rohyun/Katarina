package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.Summoner
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaARAM
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StaARAMRepository: JpaRepository<StaARAM, Long> {

    @Query("select champion_id from STA_ARAM where summoner_name = :summoner_name and season = :season", nativeQuery = true)
    fun getChampionIdBySummonerNameAndSeason(@Param("summoner_name")summoner_name: String,
                                             @Param("season")season: Int) : Int

    @Query("select ROUND(AVG(kill_count),3), ROUND(AVG(death_count),3), ROUND(AVG(assist_count),3) from STA_ARAM where champion_id = :champion_id",nativeQuery = true)
    fun getKDAByChampionId(@Param("champion_id")champion_id: Int): List<Triple<Float,Float,Float>>

    @Query("select SUM(game_all_count) from STA_ARAM where champion_id = :champion_id", nativeQuery = true)
    fun getGameAllCountByChampionId(@Param("champion_id")champion_id: Int): Int

    @Query("select SUM(game_win_count) from STA_ARAM where champion_id = :champion_id", nativeQuery = true)
    fun getGameWinCountByChampionId(@Param("champion_id")champion_id: Int): Int

}