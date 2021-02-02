package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.NormalMatch
import com.hubtwork.katarina.batchmatch.api.repository.NormalMatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NormalMatchService(private val repository: NormalMatchRepository) {

    fun create(match: NormalMatch) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): NormalMatch? =
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