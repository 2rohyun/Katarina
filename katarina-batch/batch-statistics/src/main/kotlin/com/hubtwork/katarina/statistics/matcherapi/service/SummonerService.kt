package com.hubtwork.katarina.statistics.matcherapi.service

import com.hubtwork.katarina.statistics.matcherapi.domain.Summoner
import com.hubtwork.katarina.statistics.matcherapi.repository.SummonerRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SummonerService(private val repository: SummonerRepository){
    fun create(summoner: Summoner) {
        repository.save(summoner)

    }

    fun getAllSummonerCount(): Long =
        repository.findAll().size.toLong()

    fun getAllSummonerEntry(): List<Summoner> =
        repository.findAll()

    fun getSummonersForScan(): List<Triple<Int, String, String>> {
        val summoners = repository.getSummonersToFindMatch()
        if (summoners.isNotEmpty())
        {
            return summoners.map { Triple(it.id, it.accountId, it.summonerName) }
        }
        return listOf()
    }

    fun getFirstSummonerForTest(): Summoner =
        repository.getFirstSummonerForTest()

    fun isSummonerExist(accountId: String) :Int =
        repository.checkSummonerExist(accountId)

    fun getSummonerByAccountId(accountId: String): Summoner =
        repository.getSummonerAlreadyExists(accountId)

    fun getSummonerBySummonerName(summonerName: String): Summoner =
        repository.findBySummonerName(summonerName)

    fun scannedSuccessful(id: Int) :String {
        // There couldn't be Error find summoner by given ID. ( in business logic )
        val summoner = repository.getOne(id)
        summoner.lastScanTime = LocalDateTime.now()
        repository.save(summoner)
        return "[ NOTICE ] MARK ${ summoner.summonerName }'s scanned time correctly in DB "
    }

    fun deleteSummonerBySummonerName(summonerName: String): String {
        val summoner = repository.findBySummonerName(summonerName) ?: return "[ ERR ] $summonerName is not exist in DB"
        repository.deleteById(summoner.id)
        return "[ NOTICE ] $summonerName has been deleted in DB "
    }
}