package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.SoloRank
import com.hubtwork.katarina.batchmatch.api.repository.SoloRankRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SoloRankService(val repository: SoloRankRepository) {

    fun create(match: SoloRank) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): SoloRank? =
        repository.findByIdOrNull(matchId)

    fun getFirstMatchForTest(): SoloRank? =
        repository.getFirstMatch()

    fun getAllCount(): Int =
        repository.findAll().size

    fun update() {
        TODO("Not yet implemented")
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}