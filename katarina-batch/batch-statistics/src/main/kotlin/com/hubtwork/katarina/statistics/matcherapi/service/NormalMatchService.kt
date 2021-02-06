package com.hubtwork.katarina.statistics.matcherapi.service

import com.hubtwork.katarina.statistics.matcherapi.domain.NormalMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.NormalMatchRepository
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