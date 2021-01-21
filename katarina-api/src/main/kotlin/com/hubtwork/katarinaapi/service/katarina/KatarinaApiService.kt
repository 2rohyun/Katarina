package com.hubtwork.katarinaapi.service.katarina

import com.google.gson.Gson
import com.hubtwork.katarinaapi.config.WebClientConfig
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchlistDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.summoners.SummonerDTO
import com.hubtwork.katarinaapi.service.riot.RiotApiService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Service
class KatarinaApiService(private val webClient: WebClient, private val riotApiService: RiotApiService, private val gson: Gson) {

    companion object {

        private val logger = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    fun getRecentMatchDetailsBySummonerName(summonerName: String): ResponseEntity<String> {

        val sTime = System.nanoTime()
        logger.info("Request Start ")

        /*
        val summoner: ResponseEntity<SummonerDTO> = restTemplate.exchange(
            "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/$summonerName",
            HttpMethod.GET, null, SummonerDTO::class)
         */

        val summoner = webClient.get()
            .uri("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/$summonerName")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)
            .block() ?: return ResponseEntity<String>(HttpStatus.NOT_FOUND)

        val matchList = webClient.get()
            .uri("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/${summoner.accountId}?endIndex=8&beginIndex=0")
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)
            .block() ?: return ResponseEntity<String>(HttpStatus.NOT_FOUND)

        /*
        val matches: ResponseEntity<MatchlistDTO> = restTemplate.exchange(
            "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/${summoner.body!!.accountId}?endIndex=8&beginIndex=0",
            HttpMethod.GET, null, MatchlistDTO::class)
        */
        val eTime = System.nanoTime()

        logger.info("Response [ elapsed Time : ${(eTime - sTime).toFloat()/1000000000}s ]")

        val matches = matchList.matches.map { it.gameId.toString() }



        return ResponseEntity<String>(getMatchDetails(matches), RiotApiService.httpHeader(), HttpStatus.OK)
    }

    /**
     * getMatchDetails about Summoner
     *                  RestTemplate Version
     * ( deprecated . for load balancing, adopt webclient at this workload . )
     */
    /*
    fun getMatchDetailRT(matchId: String): ResponseEntity<MatchDTO> =
        restTemplate.exchange("https://kr.api.riotgames.com/lol/match/v4/matches/$matchId", HttpMethod.GET, null, MatchDTO::class)

    fun getMatchDetailsRT(matchList: List<String>) {
        val result = mutableListOf<MatchDTO>()
        (matchList.forEach { matchId -> result.add(getMatchDetailRT(matchId).body!!) })
        println(result.map{ it-> it.mapId})
    }
    */

    fun getMatchDetail(matchId: String): Mono<MatchDTO> =
        webClient.get()
            .uri("https://kr.api.riotgames.com/lol/match/v4/matches/$matchId")
            .retrieve()
            .bodyToMono(MatchDTO::class.java)

    fun getMatchDetails(matchList: List<String>): String {
        val result = Flux.fromIterable(matchList)
            .flatMap{matchId -> getMatchDetail(matchId).onErrorResume{e -> Mono.empty()}}
            .toStream()
            .collect(Collectors.toList())
        return gson.toJson(result)
    }


}