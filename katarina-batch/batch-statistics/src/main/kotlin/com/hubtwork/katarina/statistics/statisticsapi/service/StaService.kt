package com.hubtwork.katarina.statistics.statisticsapi.service

import com.hubtwork.katarina.statistics.domain.matchlist.KatarinaMatchDTO
import com.hubtwork.katarina.statistics.domain.matchlist.MatchPlayerDTO
import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.SoloRankRepository
import com.hubtwork.katarina.statistics.matcherapi.repository.UserWithMatchRepository
import com.hubtwork.katarina.statistics.service.MatcherService
import com.hubtwork.katarina.statistics.statisticsapi.domain.*
import com.hubtwork.katarina.statistics.statisticsapi.repository.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.expression.common.ExpressionUtils.toFloat
import org.springframework.stereotype.Service

@Service
class StaService(private val staSoloRankRepository: StaSoloRankRepository,
                 private val matcherService: MatcherService,
                 private val userWithMatchRepository: UserWithMatchRepository,
                 private val staNormalMatchRepository: StaNormalMatchRepository,
                 private val staARAMRepository: StaARAMRepository,
                 private val staFlexRankRepository: StaFlexRankRepository,
                 private val staEventMatchRepository: StaEventMatchRepository
                         ){

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(StaService::class.java)
    }

    fun insertNormalMatch(match: KatarinaMatchDTO?, targetAccount: String){

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if(it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                if (it.win) {
                    gameWinCount += 1
                }

                val summonerName: String = it.summonerName

                val match = StaNormalMatch(
                    targetAccount,
                    championId,
                    kill.toFloat(),
                    death.toFloat(),
                    assist.toFloat(),
                    gameAllCount,
                    gameWinCount,
                    season,
                    summonerName,
                    lane
                )
                val saved = staNormalMatchRepository.save(match)
                logger.info("[ Normal match ] ${saved.summonerName} enrolled SuccessFull")
            }

        }
    }

    fun insertSoloRank(match: KatarinaMatchDTO?, targetAccount: String){

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if(it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                if (it.win) {
                    gameWinCount += 1
                }

                val summonerName: String = it.summonerName

                val match = StaSoloRank(
                    targetAccount,
                    championId,
                    kill.toFloat(),
                    death.toFloat(),
                    assist.toFloat(),
                    gameAllCount,
                    gameWinCount,
                    season,
                    summonerName,
                    lane
                )
                val saved = staSoloRankRepository.save(match)
                logger.info("[ Solo Rank ] ${saved.summonerName} enrolled SuccessFull")
            }

        }
    }

    fun insertFlexRank(match: KatarinaMatchDTO?, targetAccount: String){

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if(it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                if (it.win) {
                    gameWinCount += 1
                }

                val summonerName: String = it.summonerName

                val match = StaFlexRank(
                    targetAccount,
                    championId,
                    kill.toFloat(),
                    death.toFloat(),
                    assist.toFloat(),
                    gameAllCount,
                    gameWinCount,
                    season,
                    summonerName,
                    lane
                )
                val saved = staFlexRankRepository.save(match)
                logger.info("[ Flex Rank ] ${saved.summonerName} enrolled SuccessFull")
            }

        }
    }

    fun insertARAM(match: KatarinaMatchDTO?, targetAccount: String){

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if(it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                if (it.win) {
                    gameWinCount += 1
                }

                val summonerName: String = it.summonerName

                val match = StaARAM(
                    targetAccount,
                    championId,
                    kill.toFloat(),
                    death.toFloat(),
                    assist.toFloat(),
                    gameAllCount,
                    gameWinCount,
                    season,
                    summonerName,
                    lane
                )
                val saved = staARAMRepository.save(match)
                logger.info("[ ARAM ] ${saved.summonerName} enrolled SuccessFull")
            }

        }
    }

    fun insertEventMatch(match: KatarinaMatchDTO?, targetAccount: String){

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if(it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                if (it.win) {
                    gameWinCount += 1
                }

                val summonerName: String = it.summonerName

                val match = StaEventMatch(
                    targetAccount,
                    championId,
                    kill.toFloat(),
                    death.toFloat(),
                    assist.toFloat(),
                    gameAllCount,
                    gameWinCount,
                    season,
                    summonerName,
                    lane
                )
                val saved = staEventMatchRepository.save(match)
                logger.info("[ Event Match ] ${saved.summonerName} enrolled SuccessFull")
            }

        }
    }

    fun insertStatisticsDB(targetAccount: String) {
        val matchAndQType: List<UserWithMatch> = userWithMatchRepository.getMatchesByAccountId(targetAccount)

        matchAndQType.forEach {
            val eachMatch = matcherService.matchReader(it.matchId, it.queueType)
            logger.info("[ Notice ] ${eachMatch?.matchId} Enroll Process Start...")
            when(it.queueType) {
                420 -> insertSoloRank(eachMatch,targetAccount)
                430 -> insertNormalMatch(eachMatch,targetAccount)
                440 -> insertFlexRank(eachMatch,targetAccount)
                450 -> insertARAM(eachMatch,targetAccount)
                else -> insertEventMatch(eachMatch,targetAccount)
            }
        }
    }
}




