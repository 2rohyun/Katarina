package com.hubtwork.cdn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CdnApplication

fun main(args: Array<String>) {
    runApplication<CdnApplication>(*args)
}
