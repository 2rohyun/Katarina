package com.hubtwork.katarina.statistics.api.domain

import javax.persistence.*

@Entity
@Table(name = "STA_event_match")
@IdClass(StaComposableKey::class)
class StaEventMatch(
    accountId: String,
    championId: Int,
    kill: Int,
    death: Int,
    assist: Int,
    gameAllCount: Int,
    gameWinCount: Int,
    season: Int
) {
    @Id
    @Column(name = "account_id")
    var accountId = accountId

    @Id
    @Column(name = "champion_id")
    var championId = championId

    @Column(name = "kill_count")
    var kill = kill

    @Column(name = "death_count")
    var death = death

    @Column(name = "assist_count")
    var assist = assist

    @Column(name = "game_all_count")
    var gameAllCount = gameAllCount

    @Column(name = "game_win_count")
    var gameWinCount = gameWinCount

    @Column(name = "season")
    var season = season

}