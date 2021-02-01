package com.hubtwork.katarina.batchmatch.service.batch

import com.hubtwork.katarina.batchmatch.api.domain.Summoner
import com.hubtwork.katarina.batchmatch.api.service.SummonerService
import com.hubtwork.katarina.batchmatch.api.service.UserWithMatchService
import com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner.SummonerDTO
import com.hubtwork.katarina.batchmatch.service.riot.RiotAPI
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SummonerPipeline(private val riotAPI: RiotAPI,
                       private val summonerService: SummonerService,
                       private val userWithMatchService: UserWithMatchService,
                       private val matcherService: MatcherService
                       ) {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SummonerPipeline::class.java)
    }

    fun pipelining() {
        logger.info("-------- Get Summoners for Scan From DB ... --------")
        val summoners = getSummonersForScan()
        if( summoners.isEmpty() ) {
            logger.info("ERR :: There's no Summoner in DB.")
            return
        }
        logger.info(" GET :: ${ summoners.size } summoners. ")
        logger.info("-----------------------------------------------------")

        logger.info("------ Start Summoner - Match Scanning Process ------")
        summoners.map { summoner ->
            val pk = summoner.first
            val accountId = summoner.second
            val summonerName = summoner.third
            logger.info("SCAN   :: [ $pk, $accountId ]")

            // 504 Error avoiding
            val summonerFromServer = getSummonerInfoForCheckFromAPI(accountId)

            if (summonerName != summonerFromServer.name) {
                var summonerToDB = summonerService.getSummonerByAccountId(accountId)
                summonerToDB.summonerName = summonerFromServer.name
                summonerService.create(summonerToDB)
            }

            // check if this match enrolled already in DB
            val matchesAlreadyEnrolled = getSummonerMatchListInDB(accountId).map { it.matchId }.toSet()

            getSummonerMatchListFromAPI(accountId, matchesAlreadyEnrolled).subList(0, 20).forEach {
                getMatchDetailByAccountId(it)
                // avoid api request call limit
                Thread.sleep(1000)
            }
            checkSummonersScanned(pk)
            Thread.sleep(1000)
        }
    }

    fun getSummonersForScan() : List<Triple<Int, String, String>> =
        summonerService.getSummonersForScan()

    fun getSummonerMatchListInDB(accountId: String) =
        userWithMatchService.getMatchListOfCurrentUser(accountId)

    fun getAllSummonerData() =
        summonerService.getAllSummonerEntry()

    fun getAllUserMatchDataCount(): Long =
        userWithMatchService.getAllDataCount()

    fun getAllSummonerDataCount(): Long =
        summonerService.getAllSummonerCount()

    fun getSummonerInfoForCheckFromAPI(accountId: String) : SummonerDTO {
        var summoner = riotAPI.getSummonerByAccountIdWithBlocking(accountId).block()
        // while summoner from API is not null ( not 504, equals not empty Mono )
        // wait and callback for avoiding 504 Response
        while( summoner == null ) {
            logger.warn("Retry Request")
            Thread.sleep(1000L)
            summoner = riotAPI.getSummonerByAccountIdWithBlocking(accountId).block()
        }
        return summoner
    }

    // if match is not in DB , INSERT matches.
    fun getMatchDetailByAccountId(matchId: Long) {
        // val matchDetail = riotAPI.getMatchById(matchId)!!.block()!!
        val matchDetail = riotAPI.getMatchByMatchIdWithBlocking(matchId).block()
        if (matchDetail != null) {
            val queueType = matchDetail.queueId
            val matchEndTime = matchDetail.gameCreation + matchDetail.gameDuration * 1000

            val pipelinedMatch = matchDetail.pipeliningToMatch()
            matcherService.matchInserter(pipelinedMatch)

            matchDetail.participantIdentities.forEach {
                val player = it.player
                val accountId = player.accountId
                userWithMatchService.create(accountId, matchId, queueType, matchEndTime)
                // If Summoner is not enrolled on DB
                if (summonerService.isSummonerExist(accountId) == 0){
                    summonerService.create(Summoner(player.platformId, player.accountId, player.summonerName, player.summonerId))
                }
                else {
                    // summoner Name can be different from before. so Check it and Update.
                    var summoner = summonerService.getSummonerByAccountId(accountId)
                    if ( summoner.summonerName != player.summonerName ) {
                        summoner.summonerName = player.summonerName
                        summonerService.create(summoner)
                    }
                }
            }
        }
        // Retry function
        else {
            logger.warn("Retry Request")
            Thread.sleep(1000L)
            getMatchDetailByAccountId(matchId)
        }
    }

    fun getSummonerMatchListFromAPI(accountId: String, alreadyScannedMatch: Set<Long>): List<Long> {
        var matchSet = mutableListOf<Long>()
        var beginIndex = 0
        while(true){
            var currentMatchList = riotAPI.getMatchListWithIndexRange100WithBlocking(accountId, beginIndex)
            // if error raised from api.
            if (currentMatchList != null) {
                val matches = currentMatchList.block()?.matches
                if (matches != null) {
                    // if there's no match
                    if ( matches.isEmpty() ) break
                    else matches
                        .filter {
                                    // Summoner's Rift
                            it.queue == 325                // ALL_RANDOM
                            || it.queue == 900      // ALL_RANDOM_URF
                            || it.queue == 1010     // ALL_RANDOM_URF_SNOW
                            || it.queue == 430      // BLIND_PICK
                            || it.queue == 400      // NORMAL DRAFT
                            || it.queue == 420      // RANK SOLO
                            || it.queue == 440      // RANK FLEX
                            || it.queue == 76       // URF
                                    // ARAM
                            || it.queue == 450      // ARAM
                            || it.queue == 78       // ARAM OneForAll Mirror
                            || it.queue == 920      // ARAM PoroKing

                        }
                        .forEach {
                        matchSet.add(it.gameId)
                    }
                }
                beginIndex += 100
                // avoid api request call limit
                Thread.sleep(1000)
            }
            // If 504 Error occurred, wait and retry with kept indexes
            else {
                logger.warn("Retry Request")
                Thread.sleep(1000L)
            }
        }
        return matchSet.minus(alreadyScannedMatch)
    }

    fun checkSummonersScanned(summonerId : Int) =
        summonerService.scannedSuccessful(summonerId)

    fun readSummonersFromDBForTest() {
        val summonerCount = summonerService.getAllSummonerCount()

        println("소환사 수 : $summonerCount")
    }

}