package com.hubtwork.katarina.batchmatch.api.repository

import com.hubtwork.katarina.batchmatch.api.domain.Summoner
import com.hubtwork.katarina.batchmatch.api.domain.UserWithMatch
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

    @Query("select * from summoner order by last_scanned limit 100", nativeQuery = true)
    fun getSummonersToFindMatch() :List<Summoner>
}