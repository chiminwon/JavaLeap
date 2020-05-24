package com.ming.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class DataSourceAop {

  @Pointcut(
      "@annotation(com.ming.anotation.Master)"
          + "&& (execution(* com.ming.service..*.insert*(..))"
          + "&& execution(* com.ming.service..*.add*(..))"
          + "&& execution(* com.ming.service..*.update*(..))"
          + "&& execution(* com.ming.service..*.edit*(..))"
          + "&& execution(* com.ming.service..*.delete*(..))"
          + "&& execution(* com.ming.service..*.remove*(..)))")
  public void masterPointcut() {}

  @Pointcut(
      "!@annotation(com.ming.anotation.Master)"
          + "&& (execution(* com.ming.service..*.select*(..))"
          + "&& execution(* com.ming.service..*.get*(..))"
          + "&& execution(* com.ming.service..*.find*(..))"
          + "&& execution(* com.ming.service..*.query*(..)))")
  public void slavePointcut() {}

  @Before("masterPointcut")
  public void beforeMaster() {

  }

  @Before("slavePointcut")
  public void beforeSlave() {

  }
}
