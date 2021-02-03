package com.hubtwork.katarina.statistics.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "matcherEntityManagerFactory",
    transactionManagerRef = "matcherTransactionManager",
    basePackages = ["com.hubtwork.katarina.batchmatch.api"]
)
class MatcherDataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.matcher")
    fun matcherDataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

        // UTF-8 :: 한글 문자열 처리
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    @Primary
    @Bean
    fun matcherEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(this.matcherDataSource())
            .packages("com.hubtwork.katarina.batchmatch.api")
            .persistenceUnit("katarina")
            .build()
    }

    @Primary
    @Bean
    fun matcherTransactionManager(builder: EntityManagerFactoryBuilder): JpaTransactionManager {
        return JpaTransactionManager(matcherEntityManagerFactory(builder).`object`!!)
    }
}