package com.dailypulse.comment_service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariDataSource masterDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public HikariDataSource replicaDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public CommentRoutingDataSource routingDataSource(HikariDataSource masterDataSource, HikariDataSource replicaDatasSource) {
        CommentRoutingDataSource commentRoutingDataSource = new CommentRoutingDataSource();
        commentRoutingDataSource.setTargetDataSource(masterDataSource, replicaDatasSource);
        return commentRoutingDataSource;
    }
}
