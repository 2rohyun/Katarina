package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.FlexRank
import com.hubtwork.katarina.batchmatch.api.repository.FlexRankRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FlexRankService(private val repository: FlexRankRepository) {
    fun create(match: FlexRank) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): FlexRank? =
        repository.findByIdOrNull(matchId)

    fun update() {
        TODO("Not yet implemented")
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}