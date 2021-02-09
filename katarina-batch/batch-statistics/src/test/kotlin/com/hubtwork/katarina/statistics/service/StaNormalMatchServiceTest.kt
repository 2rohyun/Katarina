package com.hubtwork.katarina.statistics.service

import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaNormalMatchRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class StaNormalMatchServiceTest: StatisticsApplicationTests() {

    @Autowired
    lateinit var staNormalMatchRepository: StaNormalMatchRepository

    @Test
    fun getKDAFromNormalMatchTest(){
        val uniqueChampId: MutableList<Int> = staNormalMatchRepository.getUniqueChampionIdBySummonerNameAndSeason("이로현",13)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId->
            var killSum: Float = 0F
            var deathSum: Float = 0F
            var assistSum: Float = 0F
            var kdaAvg: Any = 0
            val kda: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByChampionId(champId)

            kda.forEach {
                killSum += it.kill
                deathSum += it.death
                assistSum += it.assist
            }

            val killAvg: Float = kotlin.math.round((killSum / kda.size)*100) / 100
            val deathAvg: Float = kotlin.math.round((deathSum / kda.size)*100) / 100
            val assistAvg: Float = kotlin.math.round((assistSum / kda.size)*100) / 100

            kdaAvg = if(deathAvg != 0F) {
                kotlin.math.round(((killAvg + assistAvg / deathAvg) / kda.size) * 100) / 100
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
        val uniqueChampId: MutableList<Int> = staNormalMatchRepository.getUniqueChampionIdBySummonerNameAndSeason("이로현",13)

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
            var resultList = mutableListOf(champId,allSum.toInt(),winSum.toInt(),loseSum.toInt(),winRate)
            wholeList.add(resultList)
        }
        println(wholeList)
    }

    @Test
    fun getPreferencePositionFromNormalMatchTest(){
        val position: MutableList<String> = staNormalMatchRepository.getLaneBySummonerName("이로현",13)

        var topCount = 0
        var jugCount = 0
        var midCount = 0
        var adCount = 0
        var supCount = 0

        position.forEach { posi->
            when(posi){
                "TOP" -> topCount++
                "JUNGLE" -> jugCount++
                "MIDDLE" -> midCount++
                "BOTTOM CARRY" -> adCount++
                "BOTTOM SUPPORT" -> supCount++
            }
        }
        val lane = hashMapOf(
            "정글" to jugCount,
            "미드" to midCount,
            "원딜" to adCount,
            "서폿" to supCount,
            "탑" to topCount)

        val result = lane.toList().sortedBy { (_, value) -> value}
        println("포지션 별 플레이 횟수 : $result")

        val firstLane = result[4]
        val secondLane = result[3]

        var all = mutableListOf<Any>()
        all.add(firstLane)
        all.add(secondLane)

        println(all)
    }


}
