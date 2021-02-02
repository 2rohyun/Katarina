package com.hubtwork.katarina.statistics.api.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ARAM")
class ARAM(
    matchId: Long,
    kill: Int,
    death: Int,
    assist: Int,
    gameResult: Boolean,
    season: Int
) {
    @Id
    @Column(name = "match_id")
    var matchId = matchId

    @Column(name = "kill")
    var kill = kill

    @Column(name = "death")
    var death = death

    @Column(name = "assist")
    var assist = assist

    @Column(name = "game_result")
    var gameResult = gameResult

    @Column(name = "season")
    var season = season

}