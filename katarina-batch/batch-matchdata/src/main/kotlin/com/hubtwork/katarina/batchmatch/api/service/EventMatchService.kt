package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.EventMatch
import com.hubtwork.katarina.batchmatch.api.repository.EventMatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EventMatchService(private val repository: EventMatchRepository) {

    fun create(match: EventMatch) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): EventMatch? =
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