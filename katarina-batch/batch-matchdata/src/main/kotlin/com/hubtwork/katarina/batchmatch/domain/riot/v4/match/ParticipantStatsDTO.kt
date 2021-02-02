package com.hubtwork.katarina.batchmatch.domain.riot.v4.match

import com.hubtwork.katarinaapi.dto.katarina.match.Items
import com.hubtwork.katarinaapi.dto.katarina.match.KDA
import com.hubtwork.katarinaapi.dto.katarina.match.Perk
import com.hubtwork.katarinaapi.dto.katarina.match.Statistics
import kotlin.math.roundToInt

data class ParticipantStatsDTO(
    var participantId: Int,
    var win: Boolean,

    /**
     *  ScoreBoard ( 점수표 )
     *  1. RUNE
     *  2. SPELL
     *  3. LEVEL
     *  4. CHAMPION
     *  5. SUMMONER_NAME
     *  6. ITEMS
     *  7. K / D / A
     *  8. MINION KILLS
     *  9. EARNED GOLD
     *
     *  COMMON ( TEAM )
     *  1. BAN PICK
     *  2. OBJECTS
     */
    // RUNE
    var perkPrimaryStyle: Int,  // 메인 룬 스타일
    var perkSubStyle: Int,      // 서브 룬 스타일

    var statPerk0: Int,     // (적응형) 스탯 룬 1
    var statPerk1: Int,     // (적응형) 스탯 룬 2
    var statPerk2: Int,     // (적응형) 스탯 룬 3
    // 분석 필요
    var perk0: Int,
    var perk0Var1: Int,
    var perk0Var2: Int,
    var perk0Var3: Int,
    var perk1: Int,
    var perk1Var1: Int,
    var perk1Var2: Int,
    var perk1Var3: Int,
    var perk2: Int,
    var perk2Var1: Int,
    var perk2Var2: Int,
    var perk2Var3: Int,
    var perk3: Int,
    var perk3Var1: Int,
    var perk3Var2: Int,
    var perk3Var3: Int,
    var perk4: Int,
    var perk4Var1: Int,
    var perk4Var2: Int,
    var perk4Var3: Int,
    var perk5: Int,
    var perk5var1: Int,
    var perk5var2: Int,
    var perk5Var3: Int,

    // Items
    var item0: Int,
    var item1: Int,
    var item2: Int,
    var item3: Int,
    var item4: Int,
    var item5: Int,
    var item6: Int,


    // 업적
    var doubleKills: Int,       // 더블 킬
    var tripleKills: Int,       // 트리플 킬
    var quadraKills: Int,       // 쿼드라 킬
    var pentaKills: Int,        // 펜타 킬
    var longestTimeSpentLiving: Int, // 최다 생존시간
    /**
     *  Statistics ( 통계 )
     *  1. 전투
     *  2. 적에게 가한 피해량
     *  3. 받은 피해량 및 회복량
     *  4. 시야
     *  5. 골드
     *  6. 기타
     */
    // 1. 전투
    var kills: Int,                 // KDA
    var deaths: Int,
    var assists: Int,
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
    var firstBloodAssist: Boolean,          //  퍼블 어시스트
    var firstTowerKill: Boolean,            //  포블
    var firstTowerAssist: Boolean,          //  포블 어시스트
    var firstInhibitorKill: Boolean,        //  첫 억제기 파괴
    var firstInhibitorAssist: Boolean,      //  첫 억제기 파괴 어시스트
    // 분석 필요한 정보
    var combatPlayerScore: Int,         //
    var objectivePlayerScore: Int,
    var totalPlayerScore: Int,
    var totalScoreRank: Int,
    var playerScore0: Int,
    var playerScore1: Int,
    var playerScore2: Int,
    var playerScore3: Int,
    var playerScore4: Int,
    var playerScore5: Int,
    var playerScore6: Int,
    var playerScore7: Int,
    var playerScore8: Int,
    var playerScore9: Int,
) {

    fun getStatistics(): Statistics =
        Statistics(largestKillingSpree, largestMultiKill, KillingSprees, totalDamageDealt, physicalDamageDealt, magicDamageDealt, trueDamageDealt, totalDamageDealtToChampions, physicalDamageDealtToChampions, magicDamageDealtToChampions, trueDamageDealtToChampions, largestCriticalStrike, damageDealtToTurrets, damageDealtToObjectives, totalHeal, totalUnitsHealed, totalDamageTaken, physicalDamageTaken, magicalDamageTaken, trueDamageTaken, damageSelfMitigated, visionScore, wardsPlaced, wardsKilled, visionWardsBoughtInGame, goldEarned, goldSpent, totalMinionsKilled, neutralMinionsKilled, neutralMinionsKilledTeamJungle, neutralMinionsKilledEnemyJungle, turretKills, inhibitorKills, champLevel, sightWardsBoughtInGame, timeCCingOthers, totalTimeCrowdControlDealt, firstBloodKill, firstTowerKill)

    fun getItems(): Items =
        Items(item0, item1, item2, item3, item4, item5, item6)

    fun getPerks(): Perk = Perk(perkPrimaryStyle, perkSubStyle, perk0, perk1, perk2, perk3, perk4, perk5)

    private fun calcKDA(): Double {
        return if (deaths != 0)
            ((100 * ((kills.toDouble() + assists) / deaths)).roundToInt().toDouble())/100
        else
            (kills + assists).toDouble()
    }

    fun getKDA() = KDA(kills, deaths, assists, calcKDA())

}

