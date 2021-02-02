package com.hubtwork.katarina.batchmatch.service.riot

import com.hubtwork.katarina.batchmatch.config.WebClientConfig
import com.hubtwork.katarina.batchmatch.domain.riot.v4.match.MatchDTO
import com.hubtwork.katarina.batchmatch.domain.riot.v4.match.MatchlistDTO
import com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner.SummonerDTO
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ServerErrorException
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.util.retry.Retry
import java.time.Duration
import java.time.LocalDateTime


@Service
class RiotAPI(private val webClient: WebClient)
    :MatchV4, SummonerV4
{
    companion object {

        // v4 - summoner
        const val summoner_by_name = "/lol/summoner/v4/summoners/by-name/"
        const val summoner_by_account = "/lol/summoner/v4/summoners/by-account/"
        const val summoner_by_puuid = "/lol/summoner/v4/summoners/by-puuid/"
        const val summoner_by_summonerId = "/lol/summoner/v4/summoners/"

        // v4 - league
        const val league_by_leagueId = "/lol/league/v4/leagues/"
        const val leagueInfo_by_summonerId = "/lol/league/v4/entries/by-summoner/"
        const val leagueEntry_by_param = "/lol/league/v4/entries/"

        const val challenger_league = "/lol/league/v4/challengerleagues/by-queue/"
        const val grandmaster_league = "/lol/league/v4/grandmasterleagues/by-queue/"
        const val master_league = "/lol/league/v4/masterleagues/by-queue/"

        // v4 - match
        const val match_by_matchId = "/lol/match/v4/matches/"
        const val match_by_accountId = "/lol/match/v4/matchlists/by-account/"
        const val match_timeline_by_matchId = "/lol/match/v4/timelines/by-match/"

        fun httpHeader(): HttpHeaders {
            val header = HttpHeaders()
            header.set("Date", LocalDateTime.now().toString())
            header.set("Content-Type", "application/json;charset=utf-8")
            return header
        }

        private val logger = LoggerFactory.getLogger(WebClientConfig::class.java)

        fun uriParameterBuilder(params: Map<String, String>): String {
            var uriComponents: UriComponentsBuilder = UriComponentsBuilder.newInstance()
            for ((k, v) in params) {
                uriComponents = uriComponents.queryParam(k, v)
            }
            return uriComponents.build().toUriString()
        }

    }

    private val platform = PlatformRoutes.KR.route

    // TODO - Still Working ON
    fun getMatchByMatchIdWithBlocking(matchId: Long) :Mono<MatchDTO> =
        webClient.get()
            .uri("https://$platform$match_by_matchId$matchId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(MatchDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ MatchDetail API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<MatchDTO>()
            }

    // TODO - Still Working ON
    fun getMatchListWithIndexRange100WithBlocking(encryptedAccountId: String, beginIndex: Int) :Mono<MatchlistDTO> =
        webClient.get()
            .uri("https://$platform$match_by_accountId$encryptedAccountId?beginIndex=$beginIndex&endIndex=${beginIndex + 99}")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(MatchlistDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ MatchList API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<MatchlistDTO>()
            }

    // TODO - Still Working ON
    fun getSummonerByAccountIdWithBlocking(encryptedAccountId: String) :Mono<SummonerDTO> =
        webClient.get()
            .uri("https://$platform$summoner_by_account$encryptedAccountId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(SummonerDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<SummonerDTO>()
            }

    override fun getMatchById(matchId: Long): Mono<MatchDTO>? =
        webClient.get()
            .uri("https://$platform$match_by_matchId$matchId")
            .retrieve()
            .bodyToMono(MatchDTO::class.java)

    override fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri("https://$platform$match_by_accountId$encryptedAccountId?beginIndex=$beginIndex&endIndex=${beginIndex + 99}")
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    override fun getSummonerByName(summonerName: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_name$summonerName")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerByPUUID(encryptedPUUID: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_puuid$encryptedPUUID")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerByAccountId(encryptedAccountId: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_account$encryptedAccountId")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerBySummonerId(encryptedSummonerId: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_summonerId$encryptedSummonerId")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)


}