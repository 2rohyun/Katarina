package com.hubtwork.katarina.batchmatch.batch.playground

import com.hubtwork.katarina.batchmatch.domain.riot.v4.match.MatchDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

@SpringBootTest
class ReactorTest {

    @Test
    fun monoEmptyTest() {
        var b = Mono.empty<MatchDTO>().block()

        if ( b == null ) println("이거 널 값으로 나옴 ")

        assert(true)
    }

}