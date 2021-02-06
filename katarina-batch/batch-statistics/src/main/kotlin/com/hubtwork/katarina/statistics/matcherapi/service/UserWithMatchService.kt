package com.hubtwork.katarina.statistics.matcherapi.service

import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.UserWithMatchRepository
import org.springframework.stereotype.Service

@Service
class UserWithMatchService(private val repository: UserWithMatchRepository) {

    /**
     * matchIds :: Pair< accountId<Long>, matchEndTime<Long> >
     */
    fun create(accountId: String, matchId: Long, queueType: Int, matchEndTime: Long) {
        repository.save(UserWithMatch(accountId, matchId, queueType, matchEndTime))
    }

    fun checkIsMatchAlreadyScanned(matchId: Long): Int =
        repository.checkIsMatchAlreadyScanned(matchId)

    fun getMatchListOfCurrentUser(accountId: String): List<UserWithMatch> =
        repository.getMatchesByAccountId(accountId)

    fun getLastMatchOfCurrentUser(accountId: String): UserWithMatch? =
        repository.getLastMatchByAccountId(accountId)

    fun getAllDataCount(): Long =
        repository.findAll().size.toLong()

}