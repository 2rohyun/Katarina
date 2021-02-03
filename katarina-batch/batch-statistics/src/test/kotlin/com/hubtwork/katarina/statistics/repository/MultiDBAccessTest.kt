package com.hubtwork.katarina.statistics.repository

import com.hubtwork.katarina.batchmatch.api.repository.SummonerRepository
import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.api.domain.StaComposableKey
import com.hubtwork.katarina.statistics.api.repository.StaSoloRankRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MultiDBAccessTest: StatisticsApplicationTests() {

    @Autowired
    lateinit var summonerRepository: SummonerRepository

    @Autowired
    lateinit var soloRankRepository: StaSoloRankRepository

    @Test
    fun getMatcherDBTest(){
        summonerRepository.findAll()
            .forEach {
                println(it.summonerName)
            }
    }

    @Test
    fun getStatisticsDBTest(){
        val testStatistics = soloRankRepository.findById(StaComposableKey("test",1))

        Assertions.assertTrue(testStatistics.isPresent)

        testStatistics.ifPresent {
            println("account id : " + it.accountId)
        }

    }
}