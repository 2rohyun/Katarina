package com.hubtwork.katarina.statistics.matcherapi.domain

import javax.persistence.*

@Entity
@Table(name = "normaldraft")
class NormalMatch (
    matchId: Long,
    season: Int,
    matchJson: String,
        ){

    @Id
    @Column(name = "matchId")
    var id: Long = matchId

    @Column(name = "season")
    var season: Int = season

    @Column(name = "matchJson", columnDefinition = "TEXT")
    var matchData: String = matchJson

}