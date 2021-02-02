package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.ARAM
import com.hubtwork.katarina.batchmatch.api.repository.ARAMRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ARAMService(private val repository: ARAMRepository) {

    fun create(match: ARAM) =
        repository.save(match)

    fun getMatchByMatchId(matchId: Long): ARAM? =
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