package com.hubtwork.katarina.statistics.matcherapi.domain

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "summoner")
class Summoner (
    platform: String,
    accountId: String,
    summonerName: String,
    summonerId: String
        )
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sId")
    var id: Int = 0

    @Column(name = "platformId")
    var platform: String = platform

    @Column(name = "accountId")
    var accountId: String = accountId

    @Column(name = "summonerName")
    var summonerName: String = summonerName

    @Column(name = "summonerId")
    var summonerId: String = summonerId

    // Dummy Date for LRU Algorithm
    @Column(name = "last_scanned")
    var lastScanTime: LocalDateTime = LocalDateTime.of(2010, 12, 27, 12, 0)
}