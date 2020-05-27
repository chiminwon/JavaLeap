package com.ming.aop;

import com.ming.dbutils.MyRoutingDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAOP {

    @Pointcut("@annotation(com.ming.anotation.Master)"
            + "&& (execution(* com.ming.service..*.insert*(..))"
            + "|| execution(* com.ming.service..*.add*(..))"
            + "|| execution(* com.ming.service..*.update*(..))"
            + "|| execution(* com.ming.service..*.edit*(..))"
            + "|| execution(* com.ming.service..*.delete*(..))"
            + "|| execution(* com.ming.service..*.remove*(..)))")
    public void masterPointcut() {
    }

    @Pointcut("!@annotation(com.ming.anotation.Master)"
            + "&& (execution(* com.ming.service..*.select*(..))"
            + "|| execution(* com.ming.service..*.get*(..))"
            + "|| execution(* com.ming.service..*.find*(..))"
            + "|| execution(* com.ming.service..*.query*(..)))")
    public void slavePointcut() {
    }

    @Before("masterPointcut()")
    public void beforeMaster() {
//        OtherRoutingDataSource.setMaster();
        MyRoutingDataSource.setMaster();
    }

    @Before("slavePointcut()")
    public void beforeSlave() {
//        OtherRoutingDataSource.setSlave();
        MyRoutingDataSource.setSlave();
    }
}
