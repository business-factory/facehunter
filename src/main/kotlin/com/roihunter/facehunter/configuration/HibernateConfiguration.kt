package com.roihunter.facehunter.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class HibernateConfiguration {

    private val dbUrl = System.getenv("DB_URL")
    private val dbUserName = System.getenv("DB_USER_NAME")
    private val dbPassword = System.getenv("DB_PASSWORD")

    @Bean
    open fun dataSource(): DataSource {
        val dataSource: HikariDataSource = HikariDataSource()
        dataSource.driverClassName = "org.postgresql.Driver"
        dataSource.jdbcUrl = dbUrl
        dataSource.username = dbUserName
        dataSource.password = dbPassword
        return dataSource
    }
}