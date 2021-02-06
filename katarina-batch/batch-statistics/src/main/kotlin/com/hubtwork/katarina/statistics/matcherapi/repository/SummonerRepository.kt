package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.Summoner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SummonerRepository: JpaRepository<Summoner, Int>
{
    fun findBySummonerName(summonerName: String) :Summoner

    @Query("select count(*) from summoner where accountId= :accountId", nativeQuery = true)
    fun checkSummonerExist(@Param("accountId")accountId: String) : Int

    @Query("select * from summoner where accountId= :accountId", nativeQuery = true)
    fun getSummonerAlreadyExists(@Param("accountId")accountId: String) : Summoner

    @Query("select * from summoner order by last_scanned limit 100", nativeQuery = true)
    fun getSummonersToFindMatch() :List<Summoner>

    @Query("select * from summoner order by last_scanned limit 1", nativeQuery = true)
    fun getFirstSummonerForTest() :Summoner

    @Query("drop table summoner",nativeQuery = true)
    fun dropSummoner()
}