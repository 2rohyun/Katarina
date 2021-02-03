package com.hubtwork.katarina.batchmatch.api.domain

import javax.persistence.*

@Entity
@Table(name = "aram")
class ARAM (
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