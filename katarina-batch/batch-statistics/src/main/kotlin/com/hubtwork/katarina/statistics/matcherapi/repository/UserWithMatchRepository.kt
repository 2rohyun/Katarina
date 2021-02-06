package com.hubtwork.katarina.statistics.matcherapi.repository

import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserWithMatchRepository: JpaRepository<UserWithMatch, String>
{
    @Query("select 1 as cnt from dual " +
            "where exists ( select 1 from search_usermatch where matchId = :targetMatchId)", nativeQuery = true)
    fun checkIsMatchAlreadyScanned(@Param("targetMatchId")matchId: Long): Int

    @Query("select * from search_usermatch where accountId= :targetSummoner order by matchEndTime", nativeQuery = true)
    fun getMatchesByAccountId(@Param("targetSummoner")accountId: String): List<UserWithMatch>


    @Query("select * from search_usermatch where accountId= :targetSummoner order by matchEndTime limit 1", nativeQuery = true)
    fun getLastMatchByAccountId(@Param("targetSummoner")accountId: String): UserWithMatch

    @Query("select matchId from search_usermatch where accountId= :accountId order by matchEndTime", nativeQuery = true)
    fun getMatchByAccountId(@Param("accountId")accountId: String): Long

    @Query("select queueType from search_usermatch where accountId= :accountId order by matchEndTime", nativeQuery = true)
    fun getQueueTypeByAccountId(@Param("accountId")accountId: String): Int

    @Query("select matchId,queueType from search_usermatch where accountId= :accountId order by matchEndTime", nativeQuery = true)
    fun getMatchAndQueueTypeByAccountId(@Param("accountId")accountId: String): List<Map<Long,Int>>

    @Query("drop table search_usermatch",nativeQuery = true)
    fun dropUserWithMatch()


}