package com.hubtwork.katarina.statistics.repository

import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.*
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaComposableKey
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaSoloRankRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MultiDBAccessTest: StatisticsApplicationTests() {

    @Autowired
    lateinit var summonerRepository: SummonerRepository

    @Autowired
    lateinit var aramRepository: ARAMRepository

    @Autowired
    lateinit var eventMatchRepository: EventMatchRepository

    @Autowired
    lateinit var flexRankRepository: FlexRankRepository

    @Autowired
    lateinit var matchDataRepository: MatchDataRepository

    @Autowired
    lateinit var normalMatchRepository: NormalMatchRepository

    @Autowired
    lateinit var soloRankRepository: SoloRankRepository

    @Autowired
    lateinit var userWithMatchRepository: UserWithMatchRepository

    @Autowired
    lateinit var staSoloRankRepository: StaSoloRankRepository


    @Test
    fun getMatcherDBTest(){
        summonerRepository.findAll()
            .forEach {
                println(it.summonerName)
            }
    }

    @Test
    fun getStatisticsDBTest(){
        val testStatistics = staSoloRankRepository.findById(1)

        Assertions.assertTrue(testStatistics.isPresent)

        testStatistics.ifPresent {
            println("account id : " + it.accountId)
        }

    }

    @Test
    fun test(){
    }
}