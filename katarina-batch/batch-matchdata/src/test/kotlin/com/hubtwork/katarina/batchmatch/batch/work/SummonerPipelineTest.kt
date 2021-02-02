package com.hubtwork.katarina.batchmatch.batch.work

import com.hubtwork.katarina.batchmatch.service.batch.MatcherService
import com.hubtwork.katarina.batchmatch.service.batch.SummonerPipeline
import junit.framework.*
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional


@SpringBootTest
class SummonerPipelineTest {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SummonerPipelineTest::class.java)
    }
    @Autowired
    lateinit var summonerPipeLine: SummonerPipeline

    @Autowired
    lateinit var matcherService: MatcherService

    // work
    @Test
    fun loadSummonerTest() {
        logger.info("-------- Get Summoners for Scan From DB ... --------")
        var summoners = summonerPipeLine.getSummonersForScan()
        if( summoners.isEmpty() ) {
            logger.info("ERR :: There's no Summoner in DB.")
            assertEquals(0, summoners.size)
        }
        logger.info(" GET :: ${ summoners.size } summoners. ")
        logger.info("-----------------------------------------------------")
        assertEquals(1, summoners.size)
    }

    // work
    @Test
    fun loadMatchesBySummonerTest() {
        // MIDKlNG's accountId
        logger.info("-------- Load MIDKlNG's accountID --------")
        val accountId = "w3F3IyhCmuT1DRLvkYc6bVKVMWbiMMZhuBnmc0QQTQ_zzwQ"

        logger.info("-------- Get MatchList already Enrolled In DB ... --------")
        val matchesInDB = summonerPipeLine.getSummonerMatchListInDB(accountId)
        if( matchesInDB.isEmpty() ) {
            logger.info("-------- No Already Enrolled in DB ...  --------")
            assertEquals(0, matchesInDB.size)
        }
        else {
            logger.info("-------- Some Matches Enrolled in DB ... --------")
            assertEquals(true, matchesInDB.isNotEmpty())
        }
    }

    // work
    @Test
    fun loadMatchesFromAPI() {
        // 이로's accountId
        logger.info("-------- Load MIDKlNG's accountID --------")
        val accountId = "yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram"

        logger.info("-------- Get MatchList From API ... --------")
        val matchLists = summonerPipeLine.getSummonerMatchListFromAPI(accountId, setOf())
        logger.info("-------- Matches Loaded --------")
        logger.info("Loaded Matches Size : ${matchLists.size}")
        logger.info("Loaded Matches : $matchLists")

        assertEquals(true, matchLists.isNotEmpty())
    }

    @Test
    //@Transactional
    fun loadMatchesFromApiAndInsertMatchDataOnDB() {

        // 이로현's accountId
        logger.info("-------- Load MIDKlNG's accountID --------")
        val accountId = "yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram"

        logger.info("-------- Get MatchList From API ... --------")
        val matchLists = summonerPipeLine.getSummonerMatchListFromAPI(accountId, setOf()).subList(0, 10)
        logger.info("-------- Matches Loaded --------")
        logger.info("Loaded Matches Size : ${matchLists.size}")
        logger.info("Loaded Matches : $matchLists")

        matchLists.forEach {
            summonerPipeLine.getMatchDetailByAccountId(it)
            Thread.sleep(1000)
        }

        logger.info("-------- Loaded Matches --------")
        val matchUserCount = summonerPipeLine.getAllUserMatchDataCount()
        val summonerCount = summonerPipeLine.getAllSummonerDataCount()
        val solo_match = matcherService.readFirstMatchesFromDBForTest()

        logger.info("Inserted MatchUser Data : $matchUserCount")
        logger.info("Inserted Summoner Data : $summonerCount")
        if (solo_match != null ) {
            println(" 매치아이디 : ${solo_match.id}")
            println(" 시즌 : ${solo_match.season}")
            println(" ${matcherService.matchReader(solo_match.id, 420)}")
        }

        summonerPipeLine.getAllSummonerData().forEach {
            println(it.summonerName)
        }

        assertEquals(true, summonerCount > 0)
        assertEquals(true, matchUserCount > 0)
    }

}