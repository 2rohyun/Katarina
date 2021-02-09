package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StaNormalMatchRepository: JpaRepository<StaNormalMatch, Long> {
    @Query("select distinct champion_id from STA_normal_match where summoner_name = :summoner_name and season = :season", nativeQuery = true)
    fun getUniqueChampionIdBySummonerNameAndSeason(@Param("summoner_name")summoner_name: String,
                                             @Param("season")season: Int) : MutableList<Int>

    @Query("select * from STA_normal_match where champion_id = :champion_id",nativeQuery = true)
    fun getAllByChampionId(@Param("champion_id")champion_id: Int): MutableList<StaNormalMatch>

    @Query("select lane from STA_normal_match where summoner_name = :summoner_name and season = :season", nativeQuery = true)
    fun getLaneBySummonerName(@Param("summoner_name")summoner_name: String, @Param("season")season: Int) : MutableList<String>

    @Query("select * from STA_normal_match where summoner_name = :summoner_name and season = :season and champion_id = :champion_id and lane = :lane", nativeQuery = true)
    fun checkExistedChampion(@Param("summoner_name")summoner_name: String,
                             @Param("season")season: Int,
                             @Param("champion_id")champion_id: Int,
                             @Param("lane")lane: String): MutableList<StaNormalMatch>

    @Query("update STA_normal_match set kill_count = kill_count + :kill, death_count = death_count + :death, assist_count = assist_count + :assist, game_win_count = game_win_count + :win, game_all_count = game_all_count + 1 where summoner_name = :summoner_name and season = :season and champion_id = :champion_id and lane = :lane",nativeQuery = true)
    fun updateChampionInfo(@Param("summoner_name")summoner_name: String,
                           @Param("season")season: Int,
                           @Param("champion_id")champion_id: Int,
                           @Param("kill")kill: Float,
                           @Param("death")death: Float,
                           @Param("assist")assist: Float,
                           @Param("win")win: Int,
                           @Param("lane")lane: String)
}