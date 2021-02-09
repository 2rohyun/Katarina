package com.hubtwork.katarina.statistics.statisticsapi.service

import com.hubtwork.katarina.statistics.statisticsapi.domain.StaARAM
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaEventMatch
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaARAMRepository

class StaARAMService(private val staARAMRepository: StaARAMRepository) {
    fun getKDAFromARAM(summonerName: String, season: Int): MutableList<Any> {
        val uniqueChampId: MutableList<Int> = staARAMRepository.getUniqueChampionIdBySummonerNameAndSeason(summonerName,season)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId->
            var killSum: Float = 0F
            var deathSum: Float = 0F
            var assistSum: Float = 0F
            var kdaAvg: Any = 0
            val kda: MutableList<StaARAM> = staARAMRepository.getAllByChampionId(champId)

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
            }else{ "Perfect" }

            var kdaList = mutableListOf(champId,killAvg,deathAvg,assistAvg,kdaAvg)
            wholeList.add(kdaList)
        }
        return wholeList
    }

    fun getWinRateFromARAM(summonerName: String, season: Int): MutableList<Any> {
        val uniqueChampId: MutableList<Int> = staARAMRepository.getUniqueChampionIdBySummonerNameAndSeason(summonerName,season)

        var wholeList = mutableListOf<Any>()

        uniqueChampId.forEach { champId->
            var winSum = 0f
            var allSum = 0f
            val result: MutableList<StaARAM> = staARAMRepository.getAllByChampionId(champId)

            result.forEach {
                winSum += it.gameWinCount
                allSum += it.gameAllCount
            }
            val loseSum: Float = allSum - winSum
            val winRate: Float = kotlin.math.round((((winSum / allSum) * 100))*100) / 100
            var resultList = mutableListOf(champId,allSum.toInt(),winSum.toInt(),loseSum.toInt(),winRate)
            wholeList.add(resultList)
        }
        return wholeList
    }
}