package com.hubtwork.katarinaapi.service.riot.`interface`

import com.hubtwork.katarinaapi.dto.riotapi.v4.league.LeagueListDTO
import reactor.core.publisher.Mono

interface LeagueV4 {

    fun getChallengerLeague(queue: String): Mono<LeagueListDTO>?
    fun getGrandMasterLeague(queue: String): Mono<LeagueListDTO>?
    fun getMasterLeague(queue: String): Mono<LeagueListDTO>?
    fun getAllLeague(queue: String, tier: String, division: String): Mono<LeagueListDTO>?

    fun getLeagueByLeagueId(leagueId: String): Mono<LeagueListDTO>?
    fun getLeagueBySummonerId(encryptedSummonerId: String): Mono<LeagueListDTO>?

}