package com.hubtwork.katarina.statistics.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "statisticsEntityManagerFactory",
    transactionManagerRef = "statisticsTransactionManager",
    basePackages = ["com.hubtwork.katarina.statistics.statisticsapi"]
)
class StatisticsDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.statistics")
    fun statisticsDataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

        // UTF-8 :: 한글 문자열 처리
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    @Bean
    fun statisticsEntityManagerFactory(
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(this.statisticsDataSource())
            .packages("com.hubtwork.katarina.statistics")
            .persistenceUnit("katarina")
            .build()
    }

    @Bean
    fun statisticsTransactionManager(builder: EntityManagerFactoryBuilder): JpaTransactionManager {
        return JpaTransactionManager(statisticsEntityManagerFactory(builder).`object`!!)
    }
}