package com.hubtwork.katarina.statistics.matcherapi.service

import com.hubtwork.katarina.statistics.matcherapi.domain.FlexRank
import com.hubtwork.katarina.statistics.matcherapi.repository.FlexRankRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FlexRankService(private val repository: FlexRankRepository) {
    fun create(match: FlexRank) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): FlexRank? =
        repository.findByIdOrNull(matchId)

    fun getAllCount(): Int =
        repository.findAll().size

    fun update() {
        TODO("Not yet implemented")
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}