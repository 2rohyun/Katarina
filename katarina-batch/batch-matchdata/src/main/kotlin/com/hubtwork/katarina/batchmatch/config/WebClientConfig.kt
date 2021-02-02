package com.hubtwork.katarina.batchmatch.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.http.client.reactive.ReactorClientHttpConnector

import org.springframework.http.client.reactive.ClientHttpConnector

import java.util.concurrent.TimeUnit

import io.netty.handler.timeout.ReadTimeoutHandler

import io.netty.channel.ChannelOption
import reactor.netty.http.client.HttpClient


@Configuration
class WebClientConfig {

    @Value("\${riot.api-key}")
    lateinit var riotToken: String

    private val logger = LoggerFactory.getLogger(WebClientConfig::class.java)

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient =
        builder
            .filter(requestLoggerFilter())
            .filter(responseLoggerFilter())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
            .defaultHeader("X-Riot-Token", riotToken)
            .build()

    fun requestLoggerFilter() = ExchangeFilterFunction.ofRequestProcessor {
        logger.info("Logging request: ${it.method()} ${it.url()}")

        Mono.just(it)
    }

    fun responseLoggerFilter() = ExchangeFilterFunction.ofResponseProcessor {
        logger.info("Response status code: ${it.statusCode()}")

        Mono.just(it)
    }
}