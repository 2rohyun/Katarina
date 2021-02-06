package com.hubtwork.katarina.statistics.statisticsapi.domain

import javax.persistence.*

@Entity
@Table(name = "STA_ARAM")
class StaARAM(
    accountId: String,
    championId: Int,
    kill: Float,
    death: Float,
    assist: Float,
    gameAllCount: Int,
    gameWinCount: Int,
    season: Int?,
    summonerName: String,
) {

    @Id
    @Column(name = "ARAM_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ARAMId : Long = 0

    @Column(name = "account_id")
    var accountId = accountId

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

    @Column(name = "summoner_name")
    var summonerName = summonerName

}