package com.hubtwork.katarina.statistics.domain.match

data class Statistics(
    // 1. 전투
    var largestKillingSpree: Int,   // 최대 연속킬
    var largestMultiKill: Int,      // 최대 다중킬 ( 더블, 멀티, 트리플, 쿼드라, 펜타 )
    var KillingSprees: Int,     // 콤보킬 횟수
    // 적에게 가한 피해량
    var totalDamageDealt: Int,          // 적에게 가한 피해량
    var physicalDamageDealt: Int,       // - 적에게 가한 물리 피해량
    var magicDamageDealt: Int,          // - 적에게 가한 마법 피해량
    var trueDamageDealt: Int,           // - 적에게 가한 고정 피해량
    var totalDamageDealtToChampions: Int,       // 챔피언에게 가한 피해량
    var physicalDamageDealtToChampions: Int,    // - 챔피언에게 가한 물리 피해량
    var magicDamageDealtToChampions: Int,       // - 챔피언에게 가한 마법 피해량
    var trueDamageDealtToChampions: Int,        // - 챔피언에게 가한 고정 피해량
    var largestCriticalStrike: Int,     // 가장 강력한 치명타
    var damageDealtToTurrets: Int,      // 포탑에게 가한 피해량
    var damageDealtToObjectives: Int,   // 목표물에게 가한 피해량
    // 받은 피해량 및 회복량
    var totalHeal: Int,         // - 회복량
    var totalUnitsHealed: Int,
    var totalDamageTaken: Int,      // - 적에게 받은 피해량
    var physicalDamageTaken: Int,   // - 적에게 받은 물리 피해량
    var magicalDamageTaken: Int,    // - 적에게 받은 마법 피해량
    var trueDamageTaken: Int,       // - 적에게 받은 고정 피해량
    var damageSelfMitigated: Int,   // - 자신에 대한 피해 감소량
    // 시야
    var visionScore: Int,   // - 시야 점수
    var wardsPlaced: Int,   // - 와드 설치
    var wardsKilled: Int,   // - 와드 파괴
    var visionWardsBoughtInGame: Int, // - 제어 와드 구매
    // 골드
    var goldEarned: Int,    // - 골드 획득
    var goldSpent: Int,     // - 골드 사용
    var totalMinionsKilled: Int,    // - 미니언 처치
    var neutralMinionsKilled: Int,  // - 중립 미니언 처치
    var neutralMinionsKilledTeamJungle: Int,    // - 아군 정글에서 중립 미니언 처치
    var neutralMinionsKilledEnemyJungle: Int,   // - 적군 정글에서 중립 미니언 처치
    // 기타
    var turretKills: Int,       // - 포탑 파괴
    var inhibitorKills: Int,    // - 억제기 파괴

    // 부가 정보
    var champLevel: Int,

    // 핑크와드 , 시야와드 구매 개수 , 와드 설치 , 와드 제거
    var sightWardsBoughtInGame: Int,
    // CC 기 관련
    var timeCCingOthers: Int,   // 총 CC기 적용시킨 시간
    var totalTimeCrowdControlDealt: Int,        // 총 CC기 딜 적용 시간 ?
    // 업적
    var firstBloodKill: Boolean,            //  퍼블
    var firstTowerKill: Boolean,            //  포블
)