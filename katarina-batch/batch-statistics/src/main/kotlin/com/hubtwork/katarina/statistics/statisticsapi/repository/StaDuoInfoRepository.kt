package com.hubtwork.katarina.statistics.statisticsapi.repository

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaDuoInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StaDuoInfoRepository: JpaRepository<StaDuoInfo, Long> {

    @Query("select * from STA_duo_info where summoner_name = :summoner_name and duo_name = :duo_name", nativeQuery = true)
    fun checkExistedSummoner(@Param("summoner_name")summoner_name: String,
                             @Param("duo_name")duo_name: String): MutableList<StaDuoInfo>

    @Query("update STA_duo_info set game_win_count = game_win_count + :win, game_all_count = game_all_count + 1 where summoner_name = :summoner_name and duo_name = :duo_name",nativeQuery = true)
    fun updateDuoInfo(@Param("summoner_name")summoner_name: String,
                           @Param("duo_name")duo_name: String,
                           @Param("win")win: Int)

}