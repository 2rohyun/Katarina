package com.hubtwork.katarina.statistics.repository

import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.domain.matchlist.MatchPlayerDTO
import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.UserWithMatchRepository
import com.hubtwork.katarina.statistics.service.MatcherService
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaSoloRank
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaNormalMatchRepository
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaSoloRankRepository
import com.hubtwork.katarina.statistics.statisticsapi.service.StaService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.transaction.Transactional

class StatisticsDBTest: StatisticsApplicationTests() {
    @Autowired
    lateinit var userWithMatchRepository: UserWithMatchRepository

    @Autowired
    lateinit var matcherService: MatcherService

    @Autowired
    lateinit var staService: StaService

    @Test
    fun insertStatisticsDBTest() {
        //target summoner's account id
        val targetAccount: String = "yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram"

        val matchAndQType: List<UserWithMatch> = userWithMatchRepository.getMatchesByAccountId(targetAccount)
        matchAndQType.forEach {
            val eachMatch = matcherService.matchReader(it.matchId, it.queueType)

            println(eachMatch?.matchId)
            when(it.queueType) {
                420 -> staService.insertSoloRank(eachMatch,targetAccount)
                430 -> staService.insertNormalMatch(eachMatch,targetAccount)
                440 -> staService.insertFlexRank(eachMatch,targetAccount)
                450 -> staService.insertARAM(eachMatch,targetAccount)
                else -> staService.insertEventMatch(eachMatch,targetAccount)
                }
            }
        }


}
