package com.ming.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource {

    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    // 解决并发问题
    private static final ThreadLocal<String> DB_HOLDER = new ThreadLocal<>();

    public static void setMaster() {
        DB_HOLDER.set(MASTER);
        System.out.println("Switch to Master DB!");
    }

    public static void setSlave() {
        DB_HOLDER.set(SLAVE);
        System.out.println("Switch to Slave DB!");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DB_HOLDER.get();
    }
}
