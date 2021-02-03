package com.hubtwork.katarina.batchmatch.api.repository

import com.hubtwork.katarina.batchmatch.api.domain.MatchData
import com.hubtwork.katarina.batchmatch.api.domain.NormalMatch
import com.hubtwork.katarina.batchmatch.api.domain.SoloRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface NormalMatchRepository: JpaRepository<NormalMatch, Long>