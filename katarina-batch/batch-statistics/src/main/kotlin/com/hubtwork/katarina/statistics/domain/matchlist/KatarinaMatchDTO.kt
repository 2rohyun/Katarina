package com.hubtwork.katarina.statistics.domain.matchlist

data class KatarinaMatchDTO(

    var matchId: Long,
    var platformId: String, // 플랫폼 Locale
    var seasonID: Int,      // 시즌 정보
    var gameVersion: String,// 클라이언트 버전

    var gameCreation: Long, // 매치 시작 시간
    var gameDuration: Long, // 매치 지속 시간 ( sec )

    var gameType: String,
    var queueId: Int,
    var map: String,

    var players: List<MatchPlayerDTO>

)
