package com.hubtwork.katarina.statistics.domain.match


// Data about User's pick on current match.
// all Data will be delivered by type "key" on DB.
data class Pick(
    // 팀 정보
    var team: Int,

    // 소환사 정보
    var summonerName: String,
    var tierString: String,

    // 챔피언 정보
    var champion: Int,
    // 스펠 정보
    var spell1: Int,
    var spell2: Int,

    // 룬 정보
    var rune: Perk,

    // KDA 정보
    var kda: KDA,

    // 아이템 정보
    var item: Items,

    // 통계 정보
    var statistics: Statistics
)