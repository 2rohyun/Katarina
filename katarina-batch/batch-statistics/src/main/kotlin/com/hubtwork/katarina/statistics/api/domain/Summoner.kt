package com.hubtwork.katarina.statistics.api.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "summoner")
class Summoner(
    accountId: String,
    matchId: Long
) {

    @Id
    @Column(name = "account_id")
    var accountId = accountId

    @Column(name = "match_id")
    var matchId = matchId
}