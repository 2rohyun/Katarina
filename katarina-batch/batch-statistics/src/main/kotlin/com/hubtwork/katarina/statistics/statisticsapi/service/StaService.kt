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
                 private val staEventMatchRepository: StaEventMatchRepository,
                 private val staDuoInfoRepository: StaDuoInfoRepository
                         ) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(StaService::class.java)
    }

    fun insertNormalMatch(match: KatarinaMatchDTO?, targetAccount: String) {
        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if (it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                val summonerName: String = it.summonerName

                if (it.win) {
                    gameWinCount += 1
                }

                if (staNormalMatchRepository.checkExistedChampion(summonerName, season!!, championId, lane).size >= 1) {
                    staNormalMatchRepository.updateChampionInfo(
                            summonerName,
                            season!!,
                            championId,
                            kill.toFloat(),
                            death.toFloat(),
                            assist.toFloat(),
                            gameWinCount,
                            lane
                    )
                } else {
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
    }

    fun insertSoloRank(match: KatarinaMatchDTO?, targetAccount: String) {

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

            eachPlayer?.forEach {
                if (it.accountId == targetAccount) {
                    val championId: Int = it.champion
                    val assist: Int = it.kda.assist
                    val death: Int = it.kda.death
                    val kill: Int = it.kda.kill
                    val lane: String = it.lane
                    val gameAllCount: Int = 1
                    var gameWinCount: Int = 0

                    val summonerName: String = it.summonerName

                    if (it.win) {
                        gameWinCount += 1
                    }

                    if (staSoloRankRepository.checkExistedChampion(summonerName, season!!, championId, lane).size >= 1) {
                        staSoloRankRepository.updateChampionInfo(
                                summonerName,
                                season!!,
                                championId,
                                kill.toFloat(),
                                death.toFloat(),
                                assist.toFloat(),
                                gameWinCount,
                                lane
                        )
                    } else {
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
        }

    fun insertFlexRank(match: KatarinaMatchDTO?, targetAccount: String) {

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

            eachPlayer?.forEach {
                if (it.accountId == targetAccount) {
                    val championId: Int = it.champion
                    val assist: Int = it.kda.assist
                    val death: Int = it.kda.death
                    val kill: Int = it.kda.kill
                    val lane: String = it.lane
                    val gameAllCount: Int = 1
                    var gameWinCount: Int = 0

                    val summonerName: String = it.summonerName

                    if (it.win) {
                        gameWinCount += 1
                    }

                    if (staFlexRankRepository.checkExistedChampion(summonerName, season!!, championId, lane).size >= 1) {
                        staFlexRankRepository.updateChampionInfo(
                                summonerName,
                                season!!,
                                championId,
                                kill.toFloat(),
                                death.toFloat(),
                                assist.toFloat(),
                                gameWinCount,
                                lane
                        )
                    } else {
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
        }

    fun insertARAM(match: KatarinaMatchDTO?, targetAccount: String) {

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players
            eachPlayer?.forEach {
                if (it.accountId == targetAccount) {
                    val championId: Int = it.champion
                    val assist: Int = it.kda.assist
                    val death: Int = it.kda.death
                    val kill: Int = it.kda.kill
                    val lane: String = it.lane
                    val gameAllCount: Int = 1
                    var gameWinCount: Int = 0

                    val summonerName: String = it.summonerName

                    if (it.win) {
                        gameWinCount += 1
                    }

                    if (staARAMRepository.checkExistedChampion(summonerName, season!!, championId, lane).size >= 1) {
                        staARAMRepository.updateChampionInfo(
                                summonerName,
                                season!!,
                                championId,
                                kill.toFloat(),
                                death.toFloat(),
                                assist.toFloat(),
                                gameWinCount,
                                lane
                        )
                    } else {
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
        }

    fun insertEventMatch(match: KatarinaMatchDTO?, targetAccount: String) {

        val season: Int? = match?.seasonID
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        eachPlayer?.forEach {
            if (it.accountId == targetAccount) {
                val championId: Int = it.champion
                val assist: Int = it.kda.assist
                val death: Int = it.kda.death
                val kill: Int = it.kda.kill
                val lane: String = it.lane
                val gameAllCount: Int = 1
                var gameWinCount: Int = 0

                val summonerName: String = it.summonerName

                if (it.win) {
                    gameWinCount += 1
                }

                if (staEventMatchRepository.checkExistedChampion(summonerName, season!!, championId, lane).size >= 1) {
                    staEventMatchRepository.updateChampionInfo(
                            summonerName,
                            season!!,
                            championId,
                            kill.toFloat(),
                            death.toFloat(),
                            assist.toFloat(),
                            gameWinCount,
                            lane
                    )
                } else {
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
                    logger.info("[ Event match ] ${saved.summonerName} enrolled SuccessFull")
                }
            }
        }
    }

    fun insertDuoInfo(match: KatarinaMatchDTO?, targetAccount: String){
        val eachPlayer: List<MatchPlayerDTO>? = match?.players

        var teamInfo = 100
        var summonerName = ""
        var gameWinCount: Int = 0
        eachPlayer?.forEach{
            if(it.accountId == targetAccount){
                teamInfo = it.team
                summonerName = it.summonerName
                if(it.win){
                    gameWinCount = 1
                }
            }
        }

        eachPlayer?.forEach{
            if(it.accountId != targetAccount && it.team == teamInfo){
                if(staDuoInfoRepository.checkExistedSummoner(summonerName, it.summonerName).size >= 1){
                    staDuoInfoRepository.updateDuoInfo(summonerName, it.summonerName, gameWinCount)
                    staDuoInfoRepository.updateDuoInfo(it.summonerName, summonerName, gameWinCount)
                }else{
                    val summonerInfo = StaDuoInfo(targetAccount, summonerName, it.accountId, it.summonerName, gameWinCount, 1)
                    val duoInfo = StaDuoInfo(it.accountId, it.summonerName, targetAccount, summonerName,  gameWinCount, 1)

                    val savedSummoner = staDuoInfoRepository.save(summonerInfo)
                    logger.info("[ DUO_INFO ] ${savedSummoner.summonerName} enrolled SuccessFull")

                    val savedDuo = staDuoInfoRepository.save(duoInfo)
                    logger.info("[ DUO_INFO ] ${savedDuo.summonerName} enrolled SuccessFull")
                }
            }
        }
    }
    fun insertStatisticsDB(targetAccount: String) {
        val matchAndQType: List<UserWithMatch> = userWithMatchRepository.getMatchesByAccountId(targetAccount)

        matchAndQType.forEach {
            val eachMatch = matcherService.matchReader(it.matchId, it.queueType)
            logger.info("[ Notice ] ${eachMatch?.matchId} Enroll Process Start...")
            when (it.queueType) {
                420 -> insertSoloRank(eachMatch, targetAccount)
                430 -> insertNormalMatch(eachMatch, targetAccount)
                440 -> insertFlexRank(eachMatch, targetAccount)
                450 -> insertARAM(eachMatch, targetAccount)
                else -> insertEventMatch(eachMatch, targetAccount)
            }
            insertDuoInfo(eachMatch, targetAccount)
        }
    }
}



