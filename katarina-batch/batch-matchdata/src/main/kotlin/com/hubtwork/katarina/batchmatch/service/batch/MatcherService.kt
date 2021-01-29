package com.hubtwork.katarina.batchmatch.service.batch

import com.google.gson.Gson
import com.hubtwork.katarina.batchmatch.api.domain.*
import com.hubtwork.katarina.batchmatch.api.service.*
import com.hubtwork.katarina.batchmatch.domain.katarina.matchlist.KatarinaMatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MatcherService ( private val soloRank: SoloRankService,
                       private val flexRank: FlexRankService,
                       private val normalMatch: NormalMatchService,
                       private val aram: ARAMService,
                       private val eventMatch: EventMatchService,
                       private val gson: Gson
        ){

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(MatcherService::class.java)
    }

    fun insertSoloRank(matchId: Long, season: Int, matchData: String) {
        var match = SoloRank(matchId, season, matchData)
        val saved = soloRank.create(match)
        logger.info("[ Solo Rank ] ${saved.id} enrolled SuccessFull")
    }

    fun insertFlexRank(matchId: Long, season: Int, matchData: String) {
        var match = FlexRank(matchId, season, matchData)
        val saved = flexRank.create(match)
        logger.info("[ Flex Rank ] ${saved.id} enrolled SuccessFull")
    }

    fun insertNormalMatch(matchId: Long, season: Int, matchData: String) {
        var match = NormalMatch(matchId, season, matchData)
        val saved = normalMatch.create(match)
        logger.info("[ Normal Match ] ${saved.id} enrolled SuccessFull")
    }

    fun insertARAM(matchId: Long, season: Int, matchData: String) {
        var match = ARAM(matchId, season, matchData)
        val saved = aram.create(match)
        logger.info("[ ARAM ] ${saved.id} enrolled SuccessFull")
    }

    fun insertEventMatch(matchId: Long, season: Int, matchData: String) {
        var match = EventMatch(matchId, season, matchData)
        val saved = eventMatch.create(match)
        logger.info("[ Event Match ] ${saved.id} enrolled SuccessFull")
    }

    fun matchInserter(match: KatarinaMatchDTO) {
        val season :Int = match.seasonID
        val queueType :Int = match.queueId
        val matchId :Long = match.matchId
        val matchData :String = gson.toJson(match)

        logger.info("[ Notice ] $matchId Enroll Process Start...")
        when(queueType) {
            420 -> insertSoloRank(matchId, season, matchData)
            430 -> insertNormalMatch(matchId, season, matchData)
            440 -> insertFlexRank(matchId, season, matchData)
            450 -> insertARAM(matchId, season, matchData)
            else -> insertEventMatch(matchId, season, matchData)
        }
    }

    fun readSoloRank(matchId: Long) :String? {
        var saved = soloRank.getMatchByMatchId(matchId)
        return if (saved != null) {
            val matchData = saved.matchData
            logger.info("[ Solo Rank ] ${saved.id} read SuccessFull")
            matchData
        } else {
            logger.info("[ Error ] Load $matchId in Solo Rank, DB Failed.")
            null
        }
    }

    fun readFlexRank(matchId: Long) :String? {
        var saved = flexRank.getMatchByMatchId(matchId)
        return if (saved != null) {
            val matchData = saved.matchData
            logger.info("[ Solo Rank ] ${saved.id} read SuccessFull")
            matchData
        } else {
            logger.info("[ Error ] Load $matchId in Solo Rank, DB Failed.")
            null
        }
    }

    fun readNormalMatch(matchId: Long) :String? {
        var saved = normalMatch.getMatchByMatchId(matchId)
        return if (saved != null) {
            val matchData = saved.matchData
            logger.info("[ Solo Rank ] ${saved.id} read SuccessFull")
            matchData
        } else {
            logger.info("[ Error ] Load $matchId in Solo Rank, DB Failed.")
            null
        }
    }

    fun readEventMatch(matchId: Long) :String? {
        var saved = eventMatch.getMatchByMatchId(matchId)
        return if (saved != null) {
            val matchData = saved.matchData
            logger.info("[ Solo Rank ] ${saved.id} read SuccessFull")
            matchData
        } else {
            logger.info("[ Error ] Load $matchId in Solo Rank, DB Failed.")
            null
        }
    }

    fun readARAM(matchId: Long) :String? {
        var saved = aram.getMatchByMatchId(matchId)
        return if (saved != null) {
            val matchData = saved.matchData
            logger.info("[ Solo Rank ] ${saved.id} read SuccessFull")
            matchData
        } else {
            logger.info("[ Error ] Load $matchId in Solo Rank, DB Failed.")
            null
        }
    }

    fun matchReader(matchId: Long, queueType: Int) : KatarinaMatchDTO? {
        logger.info("[ Notice ] $matchId Reading Process Start...")
        val matchData = when(queueType) {
            420 -> readSoloRank(matchId)
            430 -> readNormalMatch(matchId)
            440 -> readFlexRank(matchId)
            450 -> readARAM(matchId)
            else -> readEventMatch(matchId)
        }
        matchData?.let {
            return gson.fromJson(it, KatarinaMatchDTO::class.java)
        } ?: return null
    }

    fun readFirstMatchesFromDBForTest() :SoloRank? {
        logger.info("[ Notice ] Match Scanning for All Match DB")
        return soloRank.getFirstMatchForTest()
    }
}