package com.hubtwork.katarina.statistics.service

import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaNormalMatchRepository
import com.hubtwork.katarina.statistics.statisticsapi.service.StaNormalMatchService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.lang.Float.NaN

class StaNormalMatchServiceTest: StatisticsApplicationTests() {

    @Autowired
    lateinit var staNormalMatchRepository: StaNormalMatchRepository

    @Autowired
    lateinit var staNormalMatchService: StaNormalMatchService

    @Test
    fun getKDAFromNormalMatchTest(){
        val uniqueChampId: MutableList<Int> = staNormalMatchRepository.getUniqueChampionIdByAccountIdAndSeason("yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram",13)
        var wholeList = mutableListOf<Any>()
        uniqueChampId.forEach { champId->
            var killSum: Float = 0F
            var deathSum: Float = 0F
            var assistSum: Float = 0F
            var gameCount: Int = 0
            val kda: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByChampionId(champId)

            kda.forEach {
                killSum += it.kill
                deathSum += it.death
                assistSum += it.assist
                gameCount += it.gameAllCount
            }

            val killAvg: Float = kotlin.math.round((killSum / gameCount)*100) / 100
            val deathAvg: Float = kotlin.math.round((deathSum / gameCount)*100) / 100
            val assistAvg: Float = kotlin.math.round((assistSum / gameCount)*100) / 100

            val kdaAvg = if(deathAvg != 0F) {
                kotlin.math.round(((killAvg + assistAvg) / deathAvg) * 100) / 100
            }else{
                "Perfect"
            }
            println("챔피언 id : $champId, 킬 평균 : $killAvg, 데스 평균 : $deathAvg, 어시스트 평균 : $assistAvg, KDA 평균 : $kdaAvg")

            var kdaList = mutableListOf(champId,killAvg,deathAvg,assistAvg,kdaAvg)

            wholeList.add(kdaList)
        }
        println(wholeList)
    }

    @Test
    fun getWinRateFromNormalMatchTest(){
        val uniqueChampId: MutableList<Int> = staNormalMatchRepository.getUniqueChampionIdByAccountIdAndSeason("yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram",13)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId->
            var winSum = 0f
            var allSum = 0f
            var loseSum = 0f
            val result: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByChampionId(champId)

            result.forEach {
                winSum += it.gameWinCount
                allSum += it.gameAllCount
            }
            loseSum = allSum - winSum
            val winRate: Float = kotlin.math.round((((winSum / allSum) * 100))*100) / 100
            println("챔피언 id : $champId, 총 경기 수 : ${allSum.toInt()}, 승리 : ${winSum.toInt()}, 패배 : ${loseSum.toInt()}, 승률 : $winRate%")
            var resultList = mutableListOf(champId,allSum.toInt(),winSum.toInt(),loseSum.toInt(),"$winRate%")
            wholeList.add(resultList)
        }
        println(wholeList)
    }

    @Test
    //TODO("code cleaning : every little function should be modularize")
    //TODO("extending StaNormalMatchService")
    fun getWinRateByPreferredPositionFromNormalMatchTest(){
        val position: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByAccountId("yYWOOuEqQOgAozQCxuAt4vE_eYTSX4uVitPNYtLstram",13)

        var topCount = 0f
        var jugCount = 0f
        var midCount = 0f
        var adCount = 0f
        var supCount = 0f

        var topWinCount = 0f
        var jugWinCount = 0f
        var midWinCount = 0f
        var adWinCount = 0f
        var supWinCount = 0f

        position.forEach {
            when(it.lane){
                "TOP" -> topCount += it.gameAllCount
                "JUNGLE" -> jugCount += it.gameAllCount
                "MIDDLE" -> midCount += it.gameAllCount
                "BOTTOM CARRY" -> adCount += it.gameAllCount
                "BOTTOM SUPPORT" -> supCount += it.gameAllCount
            }

            when(it.lane){
                "TOP" -> topWinCount += it.gameWinCount
                "JUNGLE" -> jugWinCount += it.gameWinCount
                "MIDDLE" -> midWinCount += it.gameWinCount
                "BOTTOM CARRY" -> adWinCount += it.gameWinCount
                "BOTTOM SUPPORT" -> supWinCount += it.gameWinCount
            }
        }

        val topWinRate: Float = kotlin.math.round((((topWinCount / topCount) * 100))*100) / 100
        val jugWinRate: Float = kotlin.math.round((((jugWinCount / jugCount) * 100))*100) / 100
        val midWinRate: Float = kotlin.math.round((((midWinCount / midCount) * 100))*100) / 100
        val adWinRate: Float = kotlin.math.round((((adWinCount / adCount) * 100))*100) / 100
        val supWinRate: Float = kotlin.math.round((((supWinCount / supCount) * 100))*100) / 100

        //TODO("handling about win rate NaN problem")

        val lane = arrayOf(
            Triple("탑", topCount.toInt(), "$topWinRate%"),
            Triple("미드", midCount.toInt(), "$midWinRate%"),
            Triple("정글", jugCount.toInt(), "$jugWinRate%"),
            Triple("원딜", adCount.toInt(), "$adWinRate%"),
            Triple("서폿", supCount.toInt(), "$supWinRate%")
        )


        lane.sortBy { it.second }

        var all = mutableListOf<Any>()
        all.add(lane[4])
        all.add(lane[3])

        println(all)
    }

}
