package com.ming.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource {

    public final static String MASTER = "master";
    public final static String SLAVE = "slave";

    private final static ThreadLocal<String> DB_HOLDER = new ThreadLocal<>();

    public static void setMaster(){
        DB_HOLDER.set(MASTER);
        System.out.println("Switch to Master DB!");
    }

    public static void setSlave(){
        DB_HOLDER.set(SLAVE);
        System.out.println("Switch to Slave DB!");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DB_HOLDER.get();
    }
}
