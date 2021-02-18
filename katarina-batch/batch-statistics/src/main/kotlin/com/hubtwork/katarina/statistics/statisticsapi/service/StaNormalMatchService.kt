package com.hubtwork.katarina.statistics.statisticsapi.service

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaNormalMatchRepository
import org.springframework.stereotype.Service

@Service
class StaNormalMatchService(private val staNormalMatchRepository: StaNormalMatchRepository) {

    fun getKDAFromNormalMatch(accountId: String, season: Int): MutableList<Any> {
        val uniqueChampId: MutableList<Int> =
            staNormalMatchRepository.getUniqueChampionIdByAccountIdAndSeason(accountId, season)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId ->
            var killSum = 0F
            var deathSum = 0F
            var assistSum = 0F
            var kdaAvg: Any
            val kda: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByChampionId(champId)

            kda.forEach {
                killSum += it.kill
                deathSum += it.death
                assistSum += it.assist
            }

            val killAvg: Float = kotlin.math.round((killSum / kda.size) * 100) / 100
            val deathAvg: Float = kotlin.math.round((deathSum / kda.size) * 100) / 100
            val assistAvg: Float = kotlin.math.round((assistSum / kda.size) * 100) / 100

            kdaAvg = if (deathAvg != 0F) {
                kotlin.math.round(((killAvg + assistAvg / deathAvg) / kda.size) * 100) / 100
            } else {
                "Perfect"
            }

            var kdaList = mutableListOf(champId, killAvg, deathAvg, assistAvg, kdaAvg)
            wholeList.add(kdaList)
        }
        return wholeList
    }

    fun getWinRateFromNormalMatch(summonerName: String, season: Int): MutableList<Any> {
        val uniqueChampId: MutableList<Int> =
            staNormalMatchRepository.getUniqueChampionIdByAccountIdAndSeason(summonerName, season)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId ->
            var winSum = 0f
            var allSum = 0f
            val result: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByChampionId(champId)

            result.forEach {
                winSum += it.gameWinCount
                allSum += it.gameAllCount
            }
            val loseSum: Float = allSum - winSum
            val winRate: Float = kotlin.math.round((((winSum / allSum) * 100)) * 100) / 100
            var resultList = mutableListOf(champId, allSum.toInt(), winSum.toInt(), loseSum.toInt(), winRate)
            wholeList.add(resultList)
        }
        return wholeList
    }

    fun getWinRateByPreferencePositionFromNormalMatch(accountId: String, season: Int): MutableList<Any> {
        val position: MutableList<StaNormalMatch> = staNormalMatchRepository.getAllByAccountId(accountId, season)
        var topCount = 0
        var jugCount = 0
        var midCount = 0
        var adCount = 0
        var supCount = 0

        position.forEach {
            when (it.lane) {
                "TOP" -> topCount += it.gameAllCount
                "JUNGLE" -> jugCount += it.gameAllCount
                "MIDDLE" -> midCount += it.gameAllCount
                "BOTTOM CARRY" -> adCount += it.gameAllCount
                "BOTTOM SUPPORT" -> supCount += it.gameAllCount
            }
        }
        val lane = hashMapOf(
            "정글" to jugCount,
            "미드" to midCount,
            "원딜" to adCount,
            "서폿" to supCount,
            "탑" to topCount
        )

        val result = lane.toList().sortedBy { (_, value) -> value }
        println("포지션 별 플레이 횟수 : $result")

        var all = mutableListOf<Any>()
        all.add(result[4])
        all.add(result[3])

        return all
    }
}



