package com.dailypulse.comment_service;

import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class CommentRoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> currentDataSource = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return "replica";
        }

        return "master";
    }

    public void setTargetDataSource(DataSource masterDataSource, DataSource replicaDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("replica", replicaDataSource);

        super.setTargetDataSources(targetDataSources);
        super.setDefaultTargetDataSource(masterDataSource);
        super.afterPropertiesSet();
    }
}
