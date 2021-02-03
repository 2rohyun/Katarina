package com.hubtwork.katarina.batchmatch.api.repository

import com.hubtwork.katarina.batchmatch.api.domain.EventMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventMatchRepository: JpaRepository<EventMatch, Long>