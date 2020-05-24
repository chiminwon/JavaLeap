package com.ming.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class OtherRoutingDataSource extends AbstractRoutingDataSource {

    public final static String MASTER = "master";
    public final static String SLAVE = "slave";

    private  static String DB_SRC;

    public static void setMaster(){
        DB_SRC=MASTER;
        System.out.println("Switch to Master DB!");
    }

    public static void setSlave(){
        DB_SRC=SLAVE;
        System.out.println("Switch to Slave DB!");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DB_SRC;
    }
}
