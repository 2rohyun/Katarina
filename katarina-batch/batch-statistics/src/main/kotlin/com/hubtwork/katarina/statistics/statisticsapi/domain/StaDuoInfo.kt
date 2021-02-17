package com.hubtwork.katarina.statistics.statisticsapi.domain

import javax.persistence.*

@Entity
@Table(name="STA_duo_info")
class StaDuoInfo(
        summonerId:String,
        summonerName:String,
        duoId:String,
        duoName:String,
        gameWinCount:Int,
        gameAllCount:Int,
){
    @Id
    @Column(name = "duo_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var duoInfoId : Long = 0

    @Column(name = "summoner_id")
    var summonerId = summonerId

    @Column(name = "summoner_name")
    var summonerName = summonerName

    @Column(name = "duo_id")
    var duoId = duoId

    @Column(name = "duo_name")
    var duoName = duoName

    @Column(name = "game_all_count")
    var gameAllCount = gameAllCount

    @Column(name = "game_win_count")
    var gameWinCount = gameWinCount
}